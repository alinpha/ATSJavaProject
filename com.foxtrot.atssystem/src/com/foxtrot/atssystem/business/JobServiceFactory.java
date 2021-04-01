/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

/**
 *
 * @author izess
 */
public abstract class JobServiceFactory {
    public static IJobService createInstance() {
        return new JobService();
    }
}
