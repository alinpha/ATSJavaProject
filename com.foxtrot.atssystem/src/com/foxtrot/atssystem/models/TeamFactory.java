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
 * @author Izes Souto1
 */
public abstract class TeamFactory {
    public static ITeam createInstance() {
        return new Team();
    }
    
    public static ITeam createInstance(String name, boolean isOnCall, boolean del, Date c, Date u, Date d, List<IEmployee> members) {
        return new Team(name,isOnCall,del,c,u,d,members);
    }
    
    public static ArrayList<ITeam> createListInstance() {
        return new ArrayList();
    }
}
