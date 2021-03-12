/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import business.Task;

/**
 *
 * @author izess
 */
public class TaskViewModel {
    
    private Task task = new Task();
    
    public TaskViewModel(){
    }
        
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    
}
