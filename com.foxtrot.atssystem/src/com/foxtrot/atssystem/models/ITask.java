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
public interface ITask extends IBase {
    int getId();
    void setId(int id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    int getDuration();
    void setDuration(int duration);
    Date getCreatedAt();
    void setCreatedAt(Date createdAt);
    Date getUpdatedAt();
    void setUpdatedAt(Date updatedAt);
    //Date getDeletedAt();
    //void setDeletedAt(Date date);
    //boolean isDeleted();
    //void setIsDeleted(boolean del);
}
