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
 * @author Aline Vetrov
 */
public abstract class EmployeeFactory {
    public static IEmployee createInstance() {
        return new Employee();
    }
    
    public static IEmployee createInstance(String fname, String lname, String sin,
            double rate, boolean del, Date c, Date u, Date d) {
        return new Employee(fname,lname,sin,rate,del,c,u,d);
    }
    
    public static ArrayList<IEmployee> createListInstance() {
        return new ArrayList();
    }
}
