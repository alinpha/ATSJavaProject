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
    
     private int id;
     private String firstName;
     private String lastName;
     private String sin;
     private double hourlyRate;
     private boolean isDeleted;
     private Date createdAt;
     private Date updatedAt;
     private Date deletedAt;
     
     public Employee() {
         
     }
     
     public Employee(String fname, String lname, String sin, double hourlyRate, 
             boolean isDeleted, Date createdAt, Date updatedAt, Date deletedAt) {
         
     }
     
     
     

//    public void setInvoiceDescription(String description) {
//        if(invoiceDescription == null) {
//            this.addError(ErrorFactory.createInstance(1, "Invoice description is required"));
//        } else if(invoiceDescription.length() < 5) {
//            this.addError(ErrorFactory.createInstance(2, "Invoice description must be 5 chars or more"));
//        } else {
//            this.invoiceDescription = description;
//        }
//        
//    }

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

    public String getSIN() {
        return sin;
    }

    public void setSIN(String sin) {
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

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

}