/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.atssystem.repository.IJobRepository;
import com.foxtrot.atssystem.repository.JobRepoFactory;

/**
 *
 * @author izess
 */
public class JobService implements IJobService {
    private final IJobRepository repo;
    
    public JobService() {
        repo = JobRepoFactory.createInstance();
    }

    @Override
    public boolean isValid(IJob job) {
        return job.getErrors().size() == 0;
    }

    @Override
    public IJob createJob(IJob job) {
        if(isValid(job)) {
            int id = repo.insertJob(job);
            job.setId(id);
        }
        
        return job;
    }
}
