/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import com.foxtrot.atssystem.business.ITaskService;
import com.foxtrot.atssystem.business.TaskServiceFactory;
import com.foxtrot.atssystem.models.ErrorFactory;
import com.foxtrot.atssystem.models.ITask;
import com.foxtrot.atssystem.models.TaskFactory;
import java.io.IOException;
import java.util.Date;
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
        ITaskService service = TaskServiceFactory.createInstance();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the task in a variable
            ITask task = service.getTask(id);

            //Set attribute as task or error
            if(task != null) {
                request.setAttribute("task", task);
            } else {
                request.setAttribute("error", new ErrorViewModel(String.format("Task id: %s not found", id)));
            }

            super.setView(request, TASKS_MAINT_VIEW);
        } else {
            //Set attribute as list of the tasks
            request.setAttribute("tasks", service.getTasks());
            super.setView(request, TASKS_VIEW);
        }

        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.setView(request, TASK_SUMMARY_VIEW);
        //Task service instance
        ITaskService service = TaskServiceFactory.createInstance();

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnTaskId");
            
            //Declare Task variable
            
            ITask tsk = TaskFactory.createInstance();
            tsk.setName(request.getParameter("taskName"));
            tsk.setDescription(request.getParameter("taskDescription"));
            tsk.setDuration(getInteger(request, "taskDuration"));

            switch (action.toLowerCase()) {
                case "create":
                    service.createTask(tsk);
                    tsk.setCreatedAt(new Date());
                    request.setAttribute("task", tsk);
                    if(!service.isValid(tsk)) {
                        request.setAttribute("errors", tsk.getErrors());
                        super.setView(request, TASKS_MAINT_VIEW);
                    }
                    break;
                case "save":
                    tsk.setId(id);
                    if(service.saveTask(tsk) == 0) {
                        tsk.addError(ErrorFactory.createInstance(14, "Failed to save task. No rows updated"));
                    } 
                    
                    service.saveTask(tsk);
                    request.setAttribute("task", tsk);
                    if(!service.isValid(tsk)) {
                        request.setAttribute("errors", tsk.getErrors());
                        super.setView(request, TASKS_MAINT_VIEW);
                    }
                    break;

                case "delete":
                   tsk.setId(id);
                   
                   if(service.deleteTask(id) == 0) {
                       tsk.addError(ErrorFactory.createInstance(10, "Could not delete task. Zero rows affected"));
                       super.setView(request, TASKS_MAINT_VIEW);
                   } 
//                   else {
//                       request.setAttribute("deleted", true);
//                       tsk.setIsDeleted(true);
//                       tsk.setDeletedAt(new Date());
//                   }
                   
                   request.setAttribute("task", tsk);
                   
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
