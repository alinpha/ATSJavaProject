/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import com.foxtrot.atssystem.business.EmployeeServiceFactory;
import com.foxtrot.atssystem.business.IEmployeeService;
import com.foxtrot.atssystem.business.TaskServiceFactory;
import com.foxtrot.atssystem.business.TeamService;
import com.foxtrot.atssystem.business.TeamServiceFactory;
import com.foxtrot.atssystem.models.EmployeeFactory;
import com.foxtrot.atssystem.models.ErrorFactory;
import com.foxtrot.atssystem.models.IEmployee;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aline Vetrov
 */
public class EmployeeController extends CommonController {

    private static final String EMPS_VIEW = "/employees.jsp";
    private static final String EMPS_MAINT_VIEW = "/employee.jsp";
    private static final String EMP_SUMMARY_VIEW = "/employeesummary.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        //Service Instance
        IEmployeeService service = EmployeeServiceFactory.createInstance();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the employee in a variable
            IEmployee employee = service.getEmployee(id);

            //Set attribute as employee or error
            if(employee != null) {
                request.setAttribute("employee", employee);
                
                request.setAttribute("tasks", TaskServiceFactory.createInstance().getTasksForEmployee(id));
                request.setAttribute("teams", TeamServiceFactory.createInstance().getTeamsForEmployee(id));
                
            } else {
                request.setAttribute("error", new ErrorViewModel(String.format("Employee id: %s not found", id)));
            }
            
            super.setView(request, EMPS_MAINT_VIEW);
        } else {
            //Set attribute as list of the employees
            request.setAttribute("employees", service.getEmployees());
            super.setView(request, EMPS_VIEW);
        }
        //request.setAttribute("boom", "lol");
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Employee service instance
        IEmployeeService service = EmployeeServiceFactory.createInstance();
        
        
        if (request.getParameter("search") != null){
            
            
            String criteria = getValue(request, "searchBy");
            String query = getValue(request, "query");
            
            List<IEmployee> list = new ArrayList();
            
            if (criteria != null && !query.equals("")) {
                
                if(criteria.equals("sin")) {
                    list = service.getEmployeesBySin("%"+query+"%");
                    
                } else if(criteria.equals("name")) {
                    list = service.getEmployeesByLastName("%"+query+"%");
                }
                
                request.setAttribute("searchResults", true);
                request.setAttribute("query", query);
            
            } else {
                list = service.getEmployees();
            }
            
            request.setAttribute("employees", list);
            super.setView(request, EMPS_VIEW);
            super.getView().forward(request, response);
            return;
            
        }

        super.setView(request, EMP_SUMMARY_VIEW);
        

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnEmployeeId");
            
            //Declare Employee variable
            IEmployee employee = EmployeeFactory.createInstance();
            employee.setFirstName(super.getValue(request, "empFirstName"));
            employee.setLastName(super.getValue(request, "empLastName"));
            employee.setSin(super.getValue(request, "empSin"));
            employee.setHourlyRate(super.getDouble(request, "empHourlyRate"));

            switch (action.toLowerCase()) {
                case "create":
                    IEmployee emp = service.createEmployee(employee);
                    emp.setCreatedAt(new Date());
                    emp.setIsDeleted(false);
                    request.setAttribute("employee", emp);
                    if(!service.isValid(employee)) {
                        request.setAttribute("errors", emp.getErrors());
                        super.setView(request, EMPS_MAINT_VIEW);
                    } 
                    break;
                case "save":
                    employee.setId(id);
                    if(service.saveEmployee(employee) == 0) {
                        employee.addError(ErrorFactory.createInstance(12, "Failed to save employee. No rows updated"));
                    } 
                    service.saveEmployee(employee);
                    request.setAttribute("employee", employee);
                    if(!service.isValid(employee)) {
                        request.setAttribute("errors", employee.getErrors());
                        super.setView(request, EMPS_MAINT_VIEW);
                    }
                    break;

                case "delete":
                    employee.setId(id);

                   if(service.deleteEmployee(id) == 0) {
                       employee.addError(ErrorFactory.createInstance(10, "Could not delete employee. Zero rows affected"));
                       super.setView(request, EMPS_MAINT_VIEW);
                   } else {
                       request.setAttribute("deleted", true);
                       employee.setIsDeleted(true);
                       employee.setDeletedAt(new Date());
                   }
                   
                   request.setAttribute("employee", employee);
                   
                    break;
            }
        } catch (Exception e) {
            super.setView(request, EMPS_MAINT_VIEW);
            request.setAttribute("error", new ErrorViewModel("An error occurred attempting to maintain employees"));
        }

        super.getView().forward(request, response);
    }

    //Create a method to reuse for creation and saving to load properties in an Employee

}
