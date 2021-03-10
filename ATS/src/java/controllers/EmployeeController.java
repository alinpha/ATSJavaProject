/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.EmployeeViewModel;
import models.ErrorViewModel;
import business.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aline Vetrov
 */
public class EmployeeController extends CommonController {

    private static final String EMPLOYEES_VIEW = "/employees.jsp";
    private static final String EMP_MAINT_VIEW = "/employee.jsp";
    private static final String EMP_SUMMARY = "/empSummary.jsp";

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee emp = new Employee();
        
        String pathInfo = request.getPathInfo();
        
        if(pathInfo == null) {
            request.setAttribute("emp", emp.getEmployees());
            super.setView(request, EMPLOYEES_VIEW);
        } else {
            String[] pathParts = pathInfo.split("/");
            
            
            if (isNumeric(pathParts[1]) || pathParts[1].equals("create")) {
                int id = super.getInteger(pathParts[1]);
            
                EmployeeViewModel vm = new EmployeeViewModel();

                if(id != 0) {
                    Employee e = emp.getEmployee(id);
                    if (e != null) {
                       //vm.setBook(b);
                    } else {
                        //no emp woth that id
                        request.setAttribute("errVm", new ErrorViewModel(String.format("Employee ID: %s not found", id)));
                    }
                }
                request.setAttribute("vm", vm);
            } else {
                request.setAttribute("errVm", new ErrorViewModel(String.format("Invalid employee ID")));

            }
            
            
            super.setView(request, EMP_MAINT_VIEW);
        }
        
        super.getView().forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.setView(request, EMP_SUMMARY);
        
        try {
            String action = super.getValue(request, "action").toLowerCase();
            Employee e = new Employee();
            int id = super.getInteger(request, "hdnEmpId");
            
            loadEmpProps(request, e);
            
            switch(action) {
                case "create":
                   // e = e.create();
                    request.setAttribute("createdEmp", e);
                    break;
                case "save":
                    //e.setId(id);
                    if(e.saveEmployee() > 0) {
                        request.setAttribute("savedEmp", e);
                    } else {
                        super.setView(request, EMP_MAINT_VIEW);
                        //BookViewModel vm = new BookViewModel();
                        //vm.setBook(b);
                        //request.setAttribute("vm", vm);
                        request.setAttribute("errVm", new ErrorViewModel("The employee does not exist and cannot be saved"));
                    }
                    break;
                case "delete":
                    //e.setId(id);
                    if(e.deleteEmployee(id)>0) {
                        request.setAttribute("deletedBook", e);
                    } else {
                        EmployeeViewModel vm = new EmployeeViewModel();
                        //vm.setBook(b);
                        request.setAttribute("vm", vm);
                        request.setAttribute("errVm", new ErrorViewModel("The book does not exist and cannot be deleted"));
                        super.setView(request, EMP_MAINT_VIEW);
                    }
                    
                    break;
            }
        } catch (Exception e) {
            super.setView(request, EMP_MAINT_VIEW);
            request.setAttribute("errVm", new ErrorViewModel("An error has ocured attempting to maintain employees "));
        }
        
        super.getView().forward(request, response);
    }
    
    //DRY - dont repeat yourself
    /**
     * Populate a employee entity from the post params of the request object
     * @param req HttpRequest
     * @param e Employee instance
     */
    private void loadEmpProps(HttpServletRequest req, Employee e) {
        //String name = super.getValue(req, "bookName");
        //double price = super.getDouble(req, "bookPrice");
        //int term = super.getInteger(req, "bookTerm");
        //b.setName(name);
        //b.setPrice(price);
        //b.setTerm(term);
    }


}
