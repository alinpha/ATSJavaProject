/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.models;

/**
 *
 * @author Aline Vetrov
 */
public class JobTask extends Base implements IJobTask {
    private IJob job;
    private ITask task;
    private double operationCost;
    private double operationRevenue;
    
    public JobTask(){}
    
    public JobTask(IJob job, ITask task, double cost, double rev) {
        setJob(job);
        setTask(task);
        setOperationCost(cost);
        setOperationRevenue(rev);
    }

    public IJob getJob() {
        return job;
    }

    public void setJob(IJob job) {
        this.job = job;
    }

    public ITask getTask() {
        return task;
    }

    public void setTask(ITask task) {
        this.task = task;
    }

    public double getOperationCost() {
        return operationCost;
    }

    public void setOperationCost(double operationCost) {
        this.operationCost = operationCost;
    }

    public double getOperationRevenue() {
        return this.operationRevenue;
    }

    public void setOperationRevenue(double operationRevenue) {
        this.operationRevenue = operationRevenue;
    }
    
    
}
