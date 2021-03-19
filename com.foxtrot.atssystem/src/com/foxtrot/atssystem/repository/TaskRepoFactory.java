/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

/**
 *
 * @author Izes Souto1
 */
public class TaskRepoFactory {
    public static ITaskRepository createInstance() {
        return new TaskRepository();
    }
}
