/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.Date;

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
    
    public Job() {
        
    }
    
    public Job(ITeam team, String desc, String cName, Date start, Date end, boolean isOnSite) {
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
        //Team - Required and 
        //must have skillset coverage to be booked for a job 
        //except off hours and the on call team is booked regardless of skills - todo in sprint 3
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
        this.clientName = clientName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
    
    
    
}
