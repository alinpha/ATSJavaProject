/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author izess
 */
public class Task implements Serializable{

    private int id;
    private String name;
    private String description;
    private int duration;
    private Date date;
    
    private List<Task> tasks = new ArrayList();
    
    public List<Task> getTasks(){
        return this.tasks;
    }
    
    public Task getTask(int id){
        List<Task> query = tasks.stream().filter(b -> b.id == id)
                .collect(Collectors.toList());
        
        if(query.size() > 0){
            return query.get(0);
        } else {
            return null;
        }
    }
    
    public Task create(){
        this.id = this.tasks.size() +1;
        return this;
    }
    
    public int saveTask(){
        return 1;
    }
    
    public int deleteTask(int id){
        return 1;
    }
    
    
//<editor-fold defaultstate="collapsed" desc="Constructors">
    public Task(){
    }
    
    public Task(int id, String name, String description, int duration, Date date){
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.date = date;
    }
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
//</editor-fold>
   
    
}
