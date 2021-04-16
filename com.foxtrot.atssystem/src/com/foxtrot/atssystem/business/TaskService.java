/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.ErrorFactory;
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
        if (isValid(task) && task.getDuration() > 30) {
            return repo.updateTask(task);
        } else {
            //task.addError(ErrorFactory.createInstance(17, "Task is not valid"));
            return 0;
        }
    }

    @Override
    public int deleteTask(int id) {
        return repo.deleteTask(id);
    }

    @Override
    public ITask getTask(int id) {
        return repo.retrieveTask(id);
    }

    @Override
    public List<ITask> getTasks() {
        return repo.retrieveTasks();
    }
    
    @Override
    public List<ITask> getTasksForEmployee(int id) {
        return repo.retrieveTasksForEmployee(id);
    }
    
}
