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
public class Task extends Base implements ITask {
    private int id;
    private String name;
    private String description;
    private int duration;
    private Date createdAt;
    private Date updatedAt;
    //private Date deletedAt;
    //private boolean isDeleted;
    
    public Task() {}
    
    public Task(String name, String desc, int duration, Date createdAt, Date updatedAt
            //, boolean isDeleted, Date deletedAt
    ) {
        setName(name);
        setDescription(desc);
        setDuration(duration);
        //setIsDeleted(isDeleted);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        //setDeletedAt(deletedAt);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
             this.addError(ErrorFactory.createInstance(1, "Name is required"));
        } else {
            this.name = name;
        }
        
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
             this.addError(ErrorFactory.createInstance(2, "Description is required"));
        } else {
            this.description = description;
        }
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        if(duration < 30) {
            this.addError(ErrorFactory.createInstance(3, "Duration must be at least 30 minutes"));
        } else if (duration % 15 != 0) {
            this.addError(ErrorFactory.createInstance(3, "Duration must be 15 minute intervals"));
        } else {
            this.duration = duration;
        }
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }    
    
//    public boolean isDeleted() {
//        return isDeleted;
//    }
//
//    public void setIsDeleted(boolean isDeleted) {
//        this.isDeleted = isDeleted;
//    }
//    
//    public Date getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Date deleteddAt) {
//        this.deletedAt = deleteddAt;
//    }
}
