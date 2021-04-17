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
import com.foxtrot.atssystem.models.EmployeeFactory;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.ITask;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aline Vetrov
 */
public class SkillsController extends CommonController {
    private static final String SKILLS_VIEW = "/skills.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        super.setView(request, SKILLS_VIEW);

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
                
                List<ITask> skills = TaskServiceFactory.createInstance().getTasksForEmployee(id);
                
                request.setAttribute("skills", skills);
                request.setAttribute("tasks", getNonAssignedTasks(skills));
                
            } else {
                request.setAttribute("error", new ErrorViewModel(String.format("Employee id: %s not found", id)));
                //request.setAttribute("employees", service.getEmployees());
                //super.setView(request, "/employees.jsp");
            }
            
            
        } else {
            //redirect to list of employees
            //request.setAttribute("employees", service.getEmployees());
            //super.setView(request, "/employees.jsp");
            request.setAttribute("error", new ErrorViewModel(String.format("Employee not found")));
        }
        
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.setView(request, SKILLS_VIEW);
        //Employee service instance
        IEmployeeService service = EmployeeServiceFactory.createInstance();

        try {
            int taskIdAdd = super.getInteger(request, "actionAdd");
            int taskIRemove = super.getInteger(request, "actionRemove");
            int empId = super.getInteger(request, "hdnEmployeeId");
            
            
            if(taskIdAdd > 0) {
                service.addEmployeeSkill(empId, taskIdAdd);
            } else if (taskIRemove > 0) {
                
                service.removeEmployeeSkill(empId, taskIRemove);
            }
            
            
            //set attributes employee and their skills + all tasks
            request.setAttribute("employee", service.getEmployee(empId));
            
            List<ITask> skills = TaskServiceFactory.createInstance().getTasksForEmployee(empId);
                
            request.setAttribute("skills", skills);
            request.setAttribute("tasks", getNonAssignedTasks(skills));
            

        } catch (Exception e) {
            request.setAttribute("error", new ErrorViewModel("An error occurred attempting to maintain employee skills"));
        }

        super.getView().forward(request, response);
    }
    
    private List<ITask> getNonAssignedTasks(List<ITask> skills) {
        List<ITask> tasks = TaskServiceFactory.createInstance().getTasks();
        
        for(ITask skill : skills) {
            tasks.removeIf(t -> t.getId() == skill.getId());
        }
        
        return tasks;
    }
}
