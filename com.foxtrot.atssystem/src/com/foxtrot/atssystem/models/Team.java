/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public class Team extends Base implements ITeam {
    
    public static final int MEMBERS_COUNT = 2;
    
    private int id;
    private String name;
    private boolean isOnCall;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    
    private List<IEmployee> members;
    
    public Team() {
        //members = EmployeeFactory.createListInstance();
        
    }
    
    public Team(String name, boolean isOnCall, boolean isDeleted, Date createdAt, Date updatedAt, Date deletedAt, List<IEmployee> members) {
        setName(name);
        setIsDeleted(isDeleted);
        setIsOnCall(isOnCall);
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        setMembers(members);
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
            this.addError(ErrorFactory.createInstance(1, "Team name is required"));
        } else {
           this.name = name; 
        }
        
    }

    public boolean isOnCall() {
        return isOnCall;
    }

    public void setIsOnCall(boolean isOnCall) {
        this.isOnCall = isOnCall;
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
    
    public List<IEmployee> getMembers() {
        if (this.members == null) {
            List<IEmployee> lst = EmployeeFactory.createListInstance();
            for(int i = 0; i<MEMBERS_COUNT; i++) {
                lst.add(EmployeeFactory.createInstance());
            }
            return lst;
        }
        return this.members;
    }
    
    public void setMembers(List<IEmployee> members) {
        if (members == null) {
            this.addError(ErrorFactory.createInstance(2, "Team members required"));
        } else if(members.size() != MEMBERS_COUNT || hasTempMembers(members)) {
            this.addError(ErrorFactory.createInstance(2, "There must be " + MEMBERS_COUNT + " members in a team"));
        } else if (hasDuplicateMembers(members)) {
            this.addError(ErrorFactory.createInstance(2, "Cannot add duplicate members to a team"));
        } else {
            this.members = members;
        }
    }
    
    /**
     * Check if list of members contain duplicates
     * @param members The list of members
     * @return true if has duplicates, false otherwise
     */
    private boolean hasDuplicateMembers(List<IEmployee> members) {
        List<Integer> ids = new ArrayList();
        for(IEmployee e : members) {
            int id = e.getId();
            if (ids.contains(id)) {
                return true;
            }
            ids.add(id);
        }
        return false;
    }
    
    /**
     * Check if list of members contain temp IEmployee
     * @param members A list of members
     * @return true if has temp IEmployee, false otherwise
     */
    private boolean hasTempMembers(List<IEmployee> members) {
        for(IEmployee e : members) {
            if (e.getId() == 0) {
                return true;
            }
        }
        return false;
    }

}
