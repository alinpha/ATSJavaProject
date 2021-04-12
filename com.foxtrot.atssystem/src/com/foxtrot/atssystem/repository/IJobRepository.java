/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.IJob;
import java.util.Date;
import java.util.List;

/**
 *
 * @author izess
 */
public interface IJobRepository {
    int insertJob(IJob job);
    boolean areJobsWithDatesExist(int teamId, Date start, Date end);
    List<IJob> retrieveJobsForToday();
}
