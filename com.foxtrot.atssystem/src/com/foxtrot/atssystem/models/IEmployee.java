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
public interface IEmployee extends IBase {
    int getId();

    void setId(int id);

    String getFirstName();

    void setFirstName(String fname);
    
    String getLastName();

    void setLastName(String lname);
    
    String getSin();

    void setSin(String sin);

    double getHourlyRate();

    void setHourlyRate(double rate);

    boolean isDeleted();

    void setIsDeleted(boolean del);

    Date getCreatedAt();

    void setCreatedAt(Date date);
    
    Date getUpdatedAt();

    void setUpdatedAt(Date date);
    
    Date getDeletedAt();

    void setDeletedAt(Date date);
}
