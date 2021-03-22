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
public interface ITeam extends IBase {
    int getId();

    void setId(int id);

    String getName();

    void setName(String name);
    
    boolean isOnCall();

    void setIsOnCall(boolean isOnCall);

    boolean isDeleted();

    void setIsDeleted(boolean del);

    Date getCreatedAt();

    void setCreatedAt(Date date);
    
    Date getUpdatedAt();

    void setUpdatedAt(Date date);
    
    Date getDeletedAt();

    void setDeletedAt(Date date);
    
    List<IEmployee> getMembers();
    
    void setMembers(List<IEmployee> members);
}
