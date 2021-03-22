/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.ITeam;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public interface ITeamService {
    boolean isValid(ITeam team);
    ITeam createTeam(ITeam team);
    int saveTeam(ITeam team);
    int deleteTeam(int id);
    
    ITeam getTeam(int id);
    List<ITeam> getTeams();
    List<ITeam> getTeamsForEmployee(int id);
}
