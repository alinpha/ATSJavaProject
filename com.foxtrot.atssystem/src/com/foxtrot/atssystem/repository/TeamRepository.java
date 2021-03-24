/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.TeamFactory;
import com.foxtrot.dataaccess.DALFactory;
import com.foxtrot.dataaccess.IDAL;
import com.foxtrot.dataaccess.IParameter;
import com.foxtrot.dataaccess.ParameterFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Izes Souto
 */
public class TeamRepository extends BaseRepository implements ITeamRepository {
    private final String SPROC_SELECT_TEAMS = "CALL selectteams(null);";
    private final String SPROC_SELECT_TEAM = "CALL selectteams(?);";
    private final String SPROC_INSERT_TEAM = "CALL insertteam(?,?,?);";
    private final String SPROC_INSERT_TEAM_MEMBER = "CALL insert_team_member(?,?);";
    
    private final String SPROC_SELECT_EMPLOYEE_TEAMS = "CALL select_employee_teams(?);";
    
    private IDAL dataAccess;
    
    public TeamRepository() {
        dataAccess = DALFactory.createInstance();
    }

    @Override
    public int insertTeam(ITeam team) {
        int id = 0;
        
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(team.getName()));
            params.add(ParameterFactory.creteInstance(team.isOnCall()));
            
            params.add(ParameterFactory.creteInstance(id, IParameter.Direction.OUT, java.sql.Types.INTEGER));
      
            List<Object> returnedValues = dataAccess.executeNonQuery(SPROC_INSERT_TEAM, params);
            
            if(returnedValues != null) {
              id = Integer.parseInt(returnedValues.get(0).toString());
              for (IEmployee emp:team.getMembers()) {
                  params = ParameterFactory.createListInstance();
                  params.add(ParameterFactory.creteInstance(emp.getId()));
                  params.add(ParameterFactory.creteInstance(id));
                  dataAccess.executeNonQuery(SPROC_INSERT_TEAM_MEMBER, params);
              }
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    @Override
    public int updateTeam(ITeam team) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteTeam(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ITeam> retrieveTeams() {
        List<ITeam> list = TeamFactory.createListInstance();
        try {
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_TEAMS, null);
            list = toListOfTeams(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
    @Override
    public List<ITeam> retrieveTeamsForEmployee(int empId) {
        List<ITeam> list = TeamFactory.createListInstance();
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(empId));
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_EMPLOYEE_TEAMS, params);
            list = toListOfTeams(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public ITeam retrieveTeam(int id) {
        List<ITeam> list = TeamFactory.createListInstance();
        
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(id));
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_TEAM, params);
            list = toListOfTeams(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    /**
     * map cached rowset to teams list
     * @param rs
     * @return
     * @throws SQLException 
     */
    private List<ITeam> toListOfTeams(CachedRowSet rs) throws SQLException {
        List<ITeam> list = TeamFactory.createListInstance();
        ITeam team;
        while(rs.next()) {
            team = TeamFactory.createInstance();
            team.setId(super.getInt("id", rs));
            team.setName(rs.getString("name"));
            team.setIsOnCall(rs.getBoolean("isOnCall"));
            team.setIsDeleted(rs.getBoolean("isDeleted"));
            team.setCreatedAt(rs.getDate("createdAt"));
            team.setUpdatedAt(rs.getDate("updatedAt"));
            team.setDeletedAt(rs.getDate("deletedAt"));
            list.add(team);
        }
        
        return list;
    }
}
