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
 * @author Izes Souto
 */
public class TaskController extends CommonController {

    private static final String TASKS_VIEW = "/tasks.jsp";
    private static final String TASKS_MAINT_VIEW = "/task.jsp";
    private static final String TASK_SUMMARY_VIEW = "/tasksummary.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        //Service Instance

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the task in a variable

            //Set attribute as task or error

            super.setView(request, TASKS_MAINT_VIEW);
        } else {
            //Set attribute as list of the tasks
            super.setView(request, TASKS_VIEW);
        }

        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.setView(request, TASK_SUMMARY_VIEW);
        //Task service instance

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnTaskId");
            
            //Declare Task variable

            switch (action.toLowerCase()) {
                case "create":
                    
                    break;
                case "save":
                    
                    break;
                case "delete":
                   
                    break;
            }
        } catch (Exception e) {
            super.setView(request, TASKS_MAINT_VIEW);
            request.setAttribute("error", new ErrorViewModel("An error occurred attempting to maintain tasks"));
        }

        super.getView().forward(request, response);
    }

    //Create a method to reuse for creation and saving to load properties in a Task

}
