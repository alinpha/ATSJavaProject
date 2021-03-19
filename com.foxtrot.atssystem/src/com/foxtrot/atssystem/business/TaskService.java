/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.ITask;
import com.foxtrot.atssystem.repository.ITaskRepository;
import com.foxtrot.atssystem.repository.TaskRepoFactory;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public class TaskService implements ITaskService {
    
    private final ITaskRepository repo;
    
    public TaskService() {
        repo = TaskRepoFactory.createInstance();
    }

    @Override
    public boolean isValid(ITask task) {
       return task.getErrors().isEmpty();
    }

    @Override
    public ITask createTask(ITask task) {
        if(isValid(task)) {
            task.setId(repo.insertTask(task));
        }
        
        return task;
    }

    @Override
    public int saveTask(ITask task) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTask(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ITask getTask(int id) {
        return repo.retrieveTask(id);
    }

    @Override
    public List<ITask> getTasks() {
        return repo.retrieveTasks();
    }
    
}
