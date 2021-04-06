/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public class Job extends Base implements IJob {
    private int id;
    private ITeam team;
    private String description;
    private String clientName;
    private Date start;
    private Date end;
    private boolean isOnSite;
    
    private List<IJobTask> jobTasks;
    
    public Job() {
        
    }
    
    public Job(ITeam team, String desc, String cName, Date start, Date end, boolean isOnSite) {
        setId(id);
        setTeam(team);
        setDescription(description);
        setClientName(cName);
        setStart(start);
        setEnd(end);
        setIsOnSite(isOnSite);
    }
    
    

    public boolean isOnSite() {
        return isOnSite;
    }

    public void setIsOnSite(boolean isOnSite) {
        this.isOnSite = isOnSite;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ITeam getTeam() {
        return team;
    }

    public void setTeam(ITeam team) {
        this.team = team;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null || description.trim().isEmpty()) {
            this.addError(ErrorFactory.createInstance(1, "Description is required"));
        } else {
            this.description = description;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        if(clientName == null || clientName.trim().isEmpty()) {
            this.addError(ErrorFactory.createInstance(3, "Client name is required"));
        } else {
            this.clientName = clientName;
        }
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        
        if (start == null) {
            this.addError(ErrorFactory.createInstance(4, "Start is required"));
        } else if (start.before(new Date())) {
            this.addError(ErrorFactory.createInstance(4, "Start must be in future"));
        } else {
            this.start = start;
        }
    }

    public Date getEnd() {
        return end;
    }
    
    public void setEnd(Date end) {
        this.end = end;
    }
    
    public List<IJobTask> getJobTasks() {
        return this.jobTasks;
    }
    
    public void setJobTasks(List<IJobTask> jobTasks) {
        this.jobTasks = jobTasks;
    }
    
}
