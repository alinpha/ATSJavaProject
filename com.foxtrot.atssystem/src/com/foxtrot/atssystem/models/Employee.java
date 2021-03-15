/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.Date;

/**
 *
 * @author Aline Vetrov
 */
public class Employee extends Base implements IEmployee {
    int id;
    String firstName;
    String lastName;
    String sin;
    double hourlyRate;
    boolean isDeleted;
    Date createdAt;
    Date updatedAt;
    Date deletedAt;
    
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
    
    //todo verify setters , add errors

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
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
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
    
    
}
