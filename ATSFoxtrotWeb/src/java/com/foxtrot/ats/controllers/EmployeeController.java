/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import java.io.IOException;
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

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the employee in a variable

            //Set attribute as employee or error

            super.setView(request, EMPS_MAINT_VIEW);
        } else {
            //Set attribute as list of the employees
            super.setView(request, EMPS_VIEW);
        }

        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.setView(request, EMP_SUMMARY_VIEW);
        //Employee service instance

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnEmployeeId");
            
            //Declare Employee variable

            switch (action.toLowerCase()) {
                case "create":
                    
                    break;
                case "save":
                    
                    break;
                case "delete":
                   
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
