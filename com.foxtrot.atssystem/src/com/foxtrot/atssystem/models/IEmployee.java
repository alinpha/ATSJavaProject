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
 * @description The Employee Model Interface. Defines properties of the Employee. Validations for the properties will
 * be implemented in the class that implements this interface
 */
public interface IEmployee extends IBase {

    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getSIN();

    void setSIN(String sin);

    double getHourlyRate();

    void setHourlyRate(double hourlyRate);

    boolean isDeleted();
    
    void setIsDeleted(boolean isDeleted);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);
    
    Date getUpdatedAt();

    void setUpdatedAt(Date updatedAt);
    
    Date getDeletedAt();

    void setDeletedAt(Date deletedAt);
}
