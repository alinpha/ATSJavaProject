/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.JobFactory;
import com.foxtrot.atssystem.models.TeamFactory;
import com.foxtrot.dataaccess.DALFactory;
import com.foxtrot.dataaccess.IDAL;
import com.foxtrot.dataaccess.IParameter;
import com.foxtrot.dataaccess.ParameterFactory;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author izess, aline
 */
public class JobRepository extends BaseRepository implements IJobRepository {
    private final String SPROC_SELECT_TODAY_JOBS = "CALL select_today_jobs();";
    private final String SPROC_SELECT_JOB = "CALL selectjobs(?);";
    private final String SPROC_INSERT_JOB = "CALL insertjob(?,?,?,?,?,?,?);";
    private final String SPROC_SELECT_JOBS_FOR_DATES = "CALL select_jobs_for_dates(?,?,?);";
    
    //private final String SPROC_SELECT_EMPLOYEE_TASKS = "CALL select_employee_tasks(?);";
    
    private IDAL dataAccess;
    
    public JobRepository() {
        dataAccess = DALFactory.createInstance();
    }
    
    @Override
    public List<IJob> retrieveJobsForToday() {
        List<IJob> list = JobFactory.createListInstance();
        try {
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_TODAY_JOBS, null);
            list = toListOfJobs(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public int insertJob(IJob job) {
        int id = 0;
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(job.getTeam().getId()));
            params.add(ParameterFactory.creteInstance(job.getDescription()));
            params.add(ParameterFactory.creteInstance(job.getClientName()));
            params.add(ParameterFactory.creteInstance(job.getStart()));
            params.add(ParameterFactory.creteInstance(job.getEnd()));
            params.add(ParameterFactory.creteInstance(job.isOnSite()));
            params.add(ParameterFactory.creteInstance(id, IParameter.Direction.OUT, java.sql.Types.INTEGER));
            
            List<Object> returnedValues = dataAccess.executeNonQuery(SPROC_INSERT_JOB, params);
            
            if(returnedValues != null) {
              id = Integer.parseInt(returnedValues.get(0).toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }
    
    @Override
    public boolean areJobsWithDatesExist(int teamId, Date start, Date end) {
         List<IJob> list = JobFactory.createListInstance();
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(teamId));
            params.add(ParameterFactory.creteInstance(start));
            params.add(ParameterFactory.creteInstance(end));
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_JOBS_FOR_DATES, params);
            //list = toListOfTasks(rs);
            return rs.size() > 0;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    /**
     * map cached rowset to employees list
     * @param rs
     * @return
     * @throws SQLException 
     */
    private List<IJob> toListOfJobs(CachedRowSet rs) throws SQLException {
        List<IJob> list = JobFactory.createListInstance();
        IJob job;
        while(rs.next()) {
            job = JobFactory.createInstance();
            job.setId(super.getInt("id", rs));
            job.setDescription(rs.getString("description"));
            job.setClientName(rs.getString("clientName"));
            job.setIsOnSite(rs.getBoolean("isOnSite"));
            job.setStart(rs.getDate("start"));
            job.setEnd(rs.getDate("end"));
            ITeam team = TeamFactory.createInstance();
            team.setId(rs.getInt("teamId"));
            job.setTeam(team);
            list.add(job);
        }
        
        return list;
    }

}
