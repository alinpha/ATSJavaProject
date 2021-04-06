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
public abstract class JobTaskFactory {
    public static IJobTask createInstance() {
        return new JobTask();
    }
    
    public static IJobTask createInstance(IJob job, ITask task, double cost, double rev) {
        return new JobTask(job,task,cost,rev);
    }
    
    public static ArrayList<IJobTask> createListInstance() {
        return new ArrayList();
    }
}
