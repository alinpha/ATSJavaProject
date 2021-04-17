/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.TeamFactory;
import com.foxtrot.atssystem.repository.ITeamRepository;
import com.foxtrot.atssystem.repository.TeamRepoFactory;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public class TeamService implements ITeamService {
    
    private final ITeamRepository repo;
    
    public TeamService() {
        repo = TeamRepoFactory.createInstance();
    }

    @Override
    public boolean isValid(ITeam team) {
        return team.getErrors().isEmpty();
    }

    @Override
    public ITeam createTeam(ITeam team) {
         if(isValid(team)) {
            team.setId(repo.insertTeam(team));
        }
        
        return team;
    }

    @Override
    public int saveTeam(ITeam team) {
        if (isValid(team) && team.isDeleted() == false ) {
            return repo.updateTeam(team);
        } else {
            return 0;
        }
    }

    @Override
    public int deleteTeam(int id) {
        return repo.deleteTeam(id);
    }

    @Override
    public ITeam getTeam(int id) {
        if (id <= 0) {
            return TeamFactory.createInstance();
        }
        return repo.retrieveTeam(id);
    }

    @Override
    public List<ITeam> getTeams() {
        return repo.retrieveTeams();
    }
    
    @Override
    public ITeam getTeamMembers(int id) {
        return repo.retrieveTeamMembers(id);
    }
    
    @Override
    public List<ITeam> getTeamsForEmployee(int id) {
        return repo.retrieveTeamsForEmployee(id);
    }
    
}
