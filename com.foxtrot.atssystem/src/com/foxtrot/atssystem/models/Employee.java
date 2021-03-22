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
 * @author Aline Vetrov
 */
public class Employee extends Base implements IEmployee {
    private int id;
    private String firstName;
    private String lastName;
    private String sin;
    private double hourlyRate;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    
    private List<ITask> tasks = TaskFactory.createListInstance();
    
    public Employee() {}
    
    public Employee(String fname, String lname, String sin,
            double hourlyRate, boolean isDeleted, Date createdAt, Date updatedAt, Date deletedAt) {
        setFirstName(fname);
        setLastName(lname);
        setSin(sin);
        setHourlyRate(hourlyRate);
        setIsDeleted(isDeleted);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null || firstName.trim().isEmpty()) {
            this.addError(ErrorFactory.createInstance(1, "First name is required"));
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null || lastName.trim().isEmpty()) {
            this.addError(ErrorFactory.createInstance(2, "Last name is required"));
        } else {
            this.lastName = lastName;
        }
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        if(sin == null) {
            this.addError(ErrorFactory.createInstance(3, "SIN is required"));
        } else if(!sin.matches("[0-9]{9,}")) {
            this.addError(ErrorFactory.createInstance(3, "SIN must be 9 digits"));
        }else {
            this.sin = sin;
        }
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if(hourlyRate <= 0) {
            this.addError(ErrorFactory.createInstance(4, "Hourly rate is required"));
        } else {
            this.hourlyRate = hourlyRate;
        }
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public void setUpdatedAt(Date updateddAt) {
        this.updatedAt = updateddAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deleteddAt) {
        this.deletedAt = deleteddAt;
    }
    
    public List<ITask> getTasks() {
        return tasks;
    }
    
    public void addToTasks(ITask task) {
        this.tasks.add(task);
    }
    
}
