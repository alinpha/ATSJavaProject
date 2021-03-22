/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.ITeam;
import java.util.List;

/**
 *
 * @author Izes Souto
 */
public interface ITeamRepository {
    int insertTeam(ITeam team);
    int updateTeam(ITeam team);
    int deleteTeam(int id);
    List<ITeam> retrieveTeams();
    ITeam retrieveTeam(int id);  
}
