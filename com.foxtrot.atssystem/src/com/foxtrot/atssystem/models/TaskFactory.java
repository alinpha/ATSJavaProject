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
 * @author Izes Souto1
 */
public abstract class TaskFactory {
    public static ITask createInstance() {
        return new Task();
    }
    
    public static ITask createInstance(String name, String desc, int duration, Date createdAt, Date updatedAt) {
        return new Task();
    }
    
    public static ArrayList<ITask> createListInstance() {
        return new ArrayList();
    }
}
