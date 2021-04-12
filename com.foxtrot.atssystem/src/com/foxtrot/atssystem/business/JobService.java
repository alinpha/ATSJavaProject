/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.ErrorFactory;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.atssystem.models.IJobTask;
import com.foxtrot.atssystem.models.ITask;
import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.JobTask;
import com.foxtrot.atssystem.repository.IJobRepository;
import com.foxtrot.atssystem.repository.JobRepoFactory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public class JobService implements IJobService {
    private final IJobRepository repo;
    
    public JobService() {
        repo = JobRepoFactory.createInstance();
    }

    @Override
    public boolean isValid(IJob job) {
       if (job.getErrors().size() > 0) {
           return false;
       }
       
       ITeam team = job.getTeam();
       if (team == null) {
           job.addError(ErrorFactory.createInstance(5, "Team is required"));
           return false;
       }
       
       int jobTime = getJobTotalTime(job);
       
       job.setEnd(calculateJobEndTime(job,jobTime));
       
       double operationRevenueMultiplier = 3.0;
       boolean isBusinessHours = isBusinessHours(job.getStart(), job.getEnd(), job.isOnSite());
       
       //Date boom = job.getStart();
       //Date lol = job.getEnd();
       
       if (!team.isOnCall()) {
           
           if (!canTeamFillTasks(job.getTeam(), job.getJobTasks())) {
                job.addError(ErrorFactory.createInstance(5, "Team " + team.getName() + " cannot fill all job tasks"));
                return false;
           }
           
           if(!isBusinessHours) {
                job.addError(ErrorFactory.createInstance(5, "Job time must be Mon-Fri between 8am and 5pm"));
                return false;
           }
       } else if (!isBusinessHours) {
           operationRevenueMultiplier = 4.0;
       }
       
       if(!isTeamAvailable(team.getId(), job.getStart(), job.getEnd())) {
            job.addError(ErrorFactory.createInstance(5, "Team " + team.getName() + " is already booked for the selected time frame"));
            return false;
       }
       
       for (IJobTask jt:job.getJobTasks()) {
           double cost = calculateOperationCost(jt, team, jobTime);
           jt.setOperationCost(cost);
           jt.setOperationRevenue(cost * operationRevenueMultiplier);
       }
       
       return true;
    }

    @Override
    public IJob createJob(IJob job) {
        if(isValid(job)) {
            int id = repo.insertJob(job);
            job.setId(id);
        }
        
        return job;
    }
    
    @Override
    public List<IJob> getJobsForToday() {
        return repo.retrieveJobsForToday();
    }
    
    
    //Business rules validations and calculations
    
    private double calculateOperationCost(IJobTask jt, ITeam team, int duration) {
        double hourlyRate = 0.0;
        for(IEmployee e:team.getMembers()) {
            hourlyRate += e.getHourlyRate();
        }
        return hourlyRate * duration / 60;
    }
    
    private Date calculateJobEndTime(IJob job, int jobTime) {
        
       Calendar c = Calendar.getInstance();
       c.setTime(job.getStart());
       c.add(Calendar.MINUTE, jobTime);
       
       return c.getTime();
    }
    
    /**
     * Calculate time in minutes for a job
     * @param job The job to calculate the time
     * @return The calculated time in minutes 
     */
    private int getJobTotalTime(IJob job) {
        //Calendar c = Calendar.getInstance();
        //c.setTime(start);
        
        List<IJobTask> jtasks = job.getJobTasks();
        ITeam team = job.getTeam();
        
        List<Integer> durations = new ArrayList();
        
        for(IEmployee e : team.getMembers()) {
            durations.add(0);
            
            for(IJobTask jt : jtasks) {
                ITask task = jt.getTask();
                if(e.containsTask(task)) {
                    int i = durations.size()-1;
                    int newValue = durations.get(i) + task.getDuration();
                    durations.set(i, newValue);
                }
            }
        }
        
        //durations.sort(Comparator.naturalOrder());
        int max = Collections.max(durations);
        
        //c.add(Calendar.MINUTE, max);
        
        //return c.getTime();
        return max;
    }
    
    /**
     * Check if start and end time of a job is in business hours
     * @param start Start date
     * @param end End date
     * @param isOnSite Is job require on site tasks
     * @return true if date are in business hours, false otherwise
     */
    private boolean isBusinessHours(Date start, Date end, boolean isOnSite) {
        //Monday through Friday 8am – 5pm
        //if on site, add 30 minutes travel time to and from 
        
        Calendar c = Calendar.getInstance();
        c.setTime(start);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY) {
            return false;
        }
        
        if (isOnSite) {
            c.add(Calendar.MINUTE, -30);
        }
        
        if (!isDateInBusineesHours(c)) {
            return false;
        }
        
        c.setTime(end);
        if (isOnSite) {
            c.add(Calendar.MINUTE, 30);
        }
        
        if (!isDateInBusineesHours(c)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Check if given date is in business hours mon-fri 8am-5pm
     * @param c Calendar representing a date to check
     * @return true if business day, false otherwise
     */
    private boolean isDateInBusineesHours(Calendar c) {
        
        int hour = c.get(Calendar.HOUR);
        int ampm = c.get(Calendar.AM_PM);
        
        boolean before8am = hour < 8 && ampm == Calendar.AM;
        boolean after5pm = hour > 5 && ampm == Calendar.PM;
        
        if (before8am || after5pm) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Check if team can fill job tasks
     * @param team The team to check
     * @param jtasks A List of job tasks to fill
     * @return true is team can fill all tasks, else otherwise
     */
    private boolean canTeamFillTasks(ITeam team, List<IJobTask> jtasks) {
        
        for(IJobTask jt:jtasks) {
            boolean canFill = false;
            for(IEmployee e:team.getMembers()) {
                if(e.containsTask(jt.getTask())) {
                    canFill = true;
                    break;
                }
            }
            if (!canFill) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Check if the team is not booked at given time frame
     * @param teamId The id of the team to check
     * @param start The start time 
     * @param end The end time
     * @return true if team was not booked, false otherwise
     */
    private boolean isTeamAvailable(int teamId, Date start, Date end) {
        
        return !repo.areJobsWithDatesExist(teamId, start, end);
    }
    
    
    
    
}
