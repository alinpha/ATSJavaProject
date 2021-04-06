/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;


import java.util.Date;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public interface IJob extends IBase {
    int getId();
    void setId(int id);
    ITeam getTeam();
    void setTeam(ITeam team);
    String getDescription();
    void setDescription(String description);
    String getClientName();
    void setClientName(String clientName);
    Date getStart();
    void setStart(Date start);
    Date getEnd();
    void setEnd(Date end);
    boolean isOnSite();
    void setIsOnSite(boolean isOnSite);
    List<IJobTask> getJobTasks();
    void setJobTasks(List<IJobTask> tasks);
}
