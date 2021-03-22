/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

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
    
    private IDAL dataAccess;
    
    public TeamRepository() {
        dataAccess = DALFactory.createInstance();
    }

    @Override
    public int insertTeam(ITeam team) {
        int id = 0;
        
        try {
            List<IParameter> params = new ArrayList();
            params.add(ParameterFactory.creteInstance(team.getName()));
            params.add(ParameterFactory.creteInstance(team.isOnCall()));
            
            params.add(ParameterFactory.creteInstance(id, IParameter.Direction.OUT, java.sql.Types.INTEGER));
      
            List<Object> returnedValues = dataAccess.executeNonQuery(SPROC_INSERT_TEAM, params);
            
            if(returnedValues != null) {
              id = Integer.parseInt(returnedValues.get(0).toString());
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
            team.setName(rs.getString("teamName"));
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
