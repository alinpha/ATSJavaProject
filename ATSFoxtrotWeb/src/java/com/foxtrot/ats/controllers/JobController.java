/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import com.foxtrot.atssystem.business.EmployeeServiceFactory;
import com.foxtrot.atssystem.business.IJobService;
import com.foxtrot.atssystem.business.ITeamService;
import com.foxtrot.atssystem.business.JobServiceFactory;
import com.foxtrot.atssystem.business.TaskServiceFactory;
import com.foxtrot.atssystem.business.TeamServiceFactory;
import com.foxtrot.atssystem.models.ErrorFactory;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.atssystem.models.ITask;
import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.JobFactory;
import com.foxtrot.atssystem.models.JobTaskFactory;
import com.foxtrot.atssystem.models.TeamFactory;
import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
            List<IJob> jobs = service.getJobsForToday();
            ITeamService s = TeamServiceFactory.createInstance();
            for(IJob job:jobs) {
                job.setTeam(s.getTeam(job.getTeam().getId()));
            }
            request.setAttribute("jobs", jobs);
            super.setView(request, JOBS_VIEW);
        }
        //request.setAttribute("boom", "lol");
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        super.setView(request, JOB_SUMMARY_VIEW);
        //job service instance
        IJobService service = JobServiceFactory.createInstance();

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnJobId");
            
            //Declare job variable
            IJob job = JobFactory.createInstance();
            job.setDescription(super.getValue(request, "jobDesc"));
            job.setClientName(getValue(request, "clientName"));
            //String[] taskIds = request.getParameterValues("jobtasks");
            job.setJobTasks(JobTaskFactory.createListInstance());
            
            String[] ids = request.getParameterValues("jobtasks");
            if (ids != null) {
                for (String idStr : ids) {
                    int taskId = Integer.parseInt(idStr);
                    ITask task = TaskServiceFactory.createInstance().getTask(taskId);
                    job.getJobTasks().add(JobTaskFactory.createInstance(job, task, 0, 0));
                }
            }
            
            int teamId = getInteger(request.getParameter("selectTeam"));
            
            ITeam team = TeamServiceFactory.createInstance().getTeam(teamId);
            team.setMembers(EmployeeServiceFactory.createInstance().getEmployeesInTeam(teamId));
            for (IEmployee emp : team.getMembers()) {
                emp.setTasks(TaskServiceFactory.createInstance().getTasksForEmployee(emp.getId()));
            }
            job.setTeam(team);
            
            job.setIsOnSite(request.getParameter("boom") != null);
            
            String sDate = request.getParameter("jobStartDate");
            if (sDate != null && !sDate.equals("")) {
                String sTime = request.getParameter("jobStartTime");
                if (!(sTime == null || sTime.equals(""))) {
                    
                    try {
                        LocalDate ld = LocalDate.parse(sDate);
            
                        DateTimeFormatter f = DateTimeFormatter.ofPattern("hh:mma");

                        LocalTime lt = LocalTime.parse(sTime, f);
                        Calendar c = Calendar.getInstance();
                        c.set(ld.getYear(), ld.getMonthValue()-1, ld.getDayOfMonth(), lt.getHour(), lt.getMinute(), 0);
                        job.setStart(c.getTime());
                    } catch(Exception e) {
                        job.setStart(null);
                        job.addError(ErrorFactory.createInstance(10, "Wrong format for start date or time."));
                    }
                    
                } else {
                    job.setStart(null);
                }
            } else {
                job.setStart(null);
            }
            

            switch (action.toLowerCase()) {
                case "create":
                    job = service.createJob(job);
                    request.setAttribute("job", job);
                    if(job.getId() == 0) {
                        request.setAttribute("errors", job.getErrors());
                        request.setAttribute("tasks", TaskServiceFactory.createInstance().getTasks());
                        request.setAttribute("teams", TeamServiceFactory.createInstance().getTeams());
                        super.setView(request, JOBS_MAINT_VIEW);
                    } else {
                        
                    }
                    break;
                case "save":
                    
                    break;
                case "delete":
                   
                    break;
            }
        } catch (Exception e) {
            
            request.setAttribute("error", new ErrorViewModel("An error occurred attempting to maintain jobs"));
            request.setAttribute("teams", TeamServiceFactory.createInstance().getTeams());
            request.setAttribute("tasks", TaskServiceFactory.createInstance().getTasks());
            super.setView(request, JOBS_MAINT_VIEW);
        }

        super.getView().forward(request, response);
        
    }
    
}
