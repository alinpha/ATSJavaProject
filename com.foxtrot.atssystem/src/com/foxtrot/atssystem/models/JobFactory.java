/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Izes Souto
 */
public class JobFactory {
    public static IJob createInstance() {
        return new Job();
    }
    
    public static IJob createInstance(ITeam team, String desc, String cName, Date start, Date end, boolean site) {
        return new Job(team, desc, cName, start, end, site);
    }
    
    public static ArrayList<IJob> createListInstance() {
        return new ArrayList();
    }
}
