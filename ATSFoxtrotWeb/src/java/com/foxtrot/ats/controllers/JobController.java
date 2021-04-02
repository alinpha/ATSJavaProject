/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import com.foxtrot.atssystem.business.IJobService;
import com.foxtrot.atssystem.business.JobServiceFactory;
import com.foxtrot.atssystem.business.TaskServiceFactory;
import com.foxtrot.atssystem.business.TeamServiceFactory;
import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.atssystem.models.JobFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author izess
 */
public class JobController extends CommonController {

    private static final String JOBS_VIEW = "/jobs.jsp";
    private static final String JOBS_MAINT_VIEW = "/job.jsp";
    private static final String JOB_SUMMARY_VIEW = "/jobsummary.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        //Service Instance
        IJobService service = JobServiceFactory.createInstance();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the job in a variable
            IJob job = JobFactory.createInstance();//service.getJob(id);

            //Set attribute as job or error
            if(job != null) {
                request.setAttribute("job", job);
                
                request.setAttribute("tasks", TaskServiceFactory.createInstance().getTasks());
                request.setAttribute("teams", TeamServiceFactory.createInstance().getTeams());
                
            } else {
                request.setAttribute("error", new ErrorViewModel(String.format("Team id: %s not found", id)));
            }
            
            super.setView(request, JOBS_MAINT_VIEW);
        } else {
            //Set attribute as list of the employees
            //request.setAttribute("employees", service.getJobs());
            super.setView(request, JOBS_VIEW);
        }
        //request.setAttribute("boom", "lol");
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
}
