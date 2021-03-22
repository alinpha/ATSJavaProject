/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.ITask;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public interface ITaskRepository {
    int insertTask(ITask task);
    int updateTask(ITask task);
    int deleteTask(int id);
    List<ITask> retrieveTasks();
    List<ITask> retrieveTasksForEmployee(int id);
    ITask retrieveTask(int id);  
}
