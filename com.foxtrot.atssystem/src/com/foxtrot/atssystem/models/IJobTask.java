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
public interface IJobTask extends IBase {
    IJob getJob();
    void setJob(IJob jobId);
    ITask getTask();
    void setTask(ITask task);
    double getOperationCost();
    void setOperationCost(double operationCost);
    double getOperationRevenue();
    void setOperationRevenue(double rev);
}
