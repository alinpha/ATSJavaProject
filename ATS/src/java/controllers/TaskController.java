/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import business.Task;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.ErrorViewModel;
import models.TaskViewModel;

/**
 *
 * @author izess
 */
public class TaskController extends CommonController {
    
    private static final String TASKS_VIEW = "/tasks.jsp";
    private static final String TASKS_MAINT_VIEW = "/task.jsp";
    private static final String TASKS_SUMMARY = "/taskSummary.jsp";

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
        Task task = new Task();
        
        String pathInfo = request.getPathInfo();
        
        if(pathInfo == null){
            request.setAttribute("vm", task.getTasks());
            super.setView(request, TASKS_VIEW);
        } else{
            String[] pathParts = pathInfo.split("/");
            
            if(pathParts[1].equalsIgnoreCase("create") || isNumeric(pathParts[1])){
            
            int id = super.getInteger(pathParts[1]);
            
            if(id != 0){
                Task queriedTask = task.getTask(id);
                
                if(queriedTask != null){
                    TaskViewModel vm = new TaskViewModel();
                    vm.setTask(queriedTask);
                    request.setAttribute("vm", vm);
                } else {
                    //No book of that id
                    request.setAttribute("error", new ErrorViewModel(String.format("Task ID: %s not found", id)));
                }
            } else{    
                request.setAttribute("vm", new TaskViewModel());
            }
            } else {
            
            }
            
            super.setView(request, TASKS_MAINT_VIEW);
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
        super.setView(request, TASKS_SUMMARY);
        
        try{
            String action = super.getValue(request, "action").toLowerCase();
            
            Task b = new Task();
            int id = super.getInteger(request, "hdnTaskId");
            
            switch(action){
                case "create":
                //load props
                    b = b.create();
                    request.setAttribute("createdTask", b);
                    break;
                case "save":
                //load props
                    b.setId(id);
                    
                    if(b.saveTask() > 0){
                        request.setAttribute("savedTask", b);
                    } else { 
                        TaskViewModel vm = new TaskViewModel();
                        vm.setTask(b);
                        request.setAttribute("vm", vm);
                        request.setAttribute("error", new ErrorViewModel("The task doesnt exist and cant be saved"));
                        
                        super.setView(request, TASKS_MAINT_VIEW);
                    }
                    break;
                case "delete":
                //load props
                    b.setId(id);
                    if (b.deleteTask(id) > 0){
                        request.setAttribute("deleteTask", b);
                    } else{
                        TaskViewModel vm = new TaskViewModel();
                        vm.setTask(b);
                        request.setAttribute("vm", vm);
                        request.setAttribute("error", new ErrorViewModel("The tasks doesnt exist and cant be deleted"));
                        
                        super.setView(request, TASKS_MAINT_VIEW);
                    }
                    break;
            }
            
        }catch(Exception e){
            super.setView(request, TASKS_MAINT_VIEW);
            request.setAttribute("error", new ErrorViewModel("n error has occurred attempting to maintain tasks"));
        }
        
        super.getView().forward(request, response);
    }
    
    /**
     * Populate a book entity from the post parameters of the request object
     * @param request HttpRequest
     * @param b our book instance
     */
    private void loadBookProperties(HttpServletRequest request, Task b){
        String name = super.getValue(request, "taskName");
        String description = super.getValue(request, "taskDescription");
        int duration = super.getInteger(request, "taskTerm");
        
        b.setName(name);
        b.setDescription(description);
        b.setDuration(duration);
    }
}
