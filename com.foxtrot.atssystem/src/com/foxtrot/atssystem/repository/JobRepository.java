/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.IJob;
import com.foxtrot.dataaccess.DALFactory;
import com.foxtrot.dataaccess.IDAL;
import com.foxtrot.dataaccess.IParameter;
import com.foxtrot.dataaccess.ParameterFactory;
import java.util.List;

/**
 *
 * @author izess
 */
public class JobRepository extends BaseRepository implements IJobRepository {
    private final String SPROC_SELECT_JOBS = "CALL selectjobs(null);";
    private final String SPROC_SELECT_JOB = "CALL selectjobs(?);";
    private final String SPROC_INSERT_JOB = "CALL insertjob(?,?,?,?,?,?,?);";
    
    //private final String SPROC_SELECT_EMPLOYEE_TASKS = "CALL select_employee_tasks(?);";
    
    private IDAL dataAccess;
    
    public JobRepository() {
        dataAccess = DALFactory.createInstance();
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
//
//    @Override
//    public int updateTask(ITask task) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public int deleteTask(int id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<ITask> retrieveTasks() {
//         List<ITask> list = TaskFactory.createListInstance();
//        try {
//            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_TASKS, null);
//            list = toListOfTasks(rs);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return list;
//    }
//    
//    @Override
//    public List<ITask> retrieveTasksForEmployee(int empId) {
//         List<ITask> list = TaskFactory.createListInstance();
//        try {
//            List<IParameter> params = ParameterFactory.createListInstance();
//            params.add(ParameterFactory.creteInstance(empId));
//            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_EMPLOYEE_TASKS, params);
//            list = toListOfTasks(rs);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//        return list;
//    }
//
//    @Override
//    public ITask retrieveTask(int id) {
//        List<ITask> list = TaskFactory.createListInstance();
//        
//        try {
//            List<IParameter> params = ParameterFactory.createListInstance();
//            params.add(ParameterFactory.creteInstance(id));
//            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_TASK, params);
//            list = toListOfTasks(rs);
//        } catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//        
//        if (list.size() > 0) {
//            return list.get(0);
//        }
//        return null;
//    }
//    
//    /**
//     * map cached rowset to employees list
//     * @param rs
//     * @return
//     * @throws SQLException 
//     */
//    private List<ITask> toListOfTasks(CachedRowSet rs) throws SQLException {
//        List<ITask> list = TaskFactory.createListInstance();
//        ITask tsk;
//        while(rs.next()) {
//            tsk = TaskFactory.createInstance();
//            tsk.setId(super.getInt("id", rs));
//            tsk.setName(rs.getString("name"));
//            tsk.setDescription(rs.getString("description"));
//            tsk.setDuration(rs.getInt("duration"));
//            tsk.setCreatedAt(rs.getDate("createdAt"));
//            tsk.setUpdatedAt(rs.getDate("updatedAt"));
//            list.add(tsk);
//        }
//        
//        return list;
//    }
}
