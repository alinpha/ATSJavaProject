/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.IJob;

/**
 *
 * @author izess
 */
public interface IJobService {
    boolean isValid(IJob job);
    IJob createJob(IJob job);
}
