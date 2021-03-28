/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.ats.controllers;

import com.foxtrot.ats.models.ErrorViewModel;
import com.foxtrot.atssystem.business.EmployeeServiceFactory;
import com.foxtrot.atssystem.business.ITeamService;
import com.foxtrot.atssystem.business.TeamServiceFactory;
import com.foxtrot.atssystem.models.EmployeeFactory;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.ITeam;
import com.foxtrot.atssystem.models.Team;
import com.foxtrot.atssystem.models.TeamFactory;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Izes Souto1
 */
public class TeamController extends CommonController {
    private static final String TEAMS_VIEW = "/teams.jsp";
    private static final String TEAMS_MAINT_VIEW = "/team.jsp";
    private static final String TEAM_SUMMARY_VIEW = "/teamsummary.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        //Service Instance
        ITeamService service = TeamServiceFactory.createInstance();

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");

            int id = super.getInteger(pathParts[1]);
            //Get the team in a variable
            ITeam team = service.getTeam(id);

            //Set attribute as team or error
            if(team != null) {
                
                request.setAttribute("team", team);
            } else {
                team = TeamFactory.createInstance();
                team.setId(0);
                request.setAttribute("team", team);
                request.setAttribute("error", new ErrorViewModel(String.format("Team id: %s not found", id)));
            }
            
            request.setAttribute("employees", EmployeeServiceFactory.createInstance().getEmployees());
 
            super.setView(request, TEAMS_MAINT_VIEW);
        } else {
            //Set attribute as list of the teams
            
            request.setAttribute("teams", service.getTeams());
            super.setView(request, TEAMS_VIEW);
        }
        
        super.getView().forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.setView(request, TEAM_SUMMARY_VIEW);
        //team service instance
        ITeamService service = TeamServiceFactory.createInstance();

        try {
            String action = super.getValue(request, "action");
            int id = super.getInteger(request, "hdnTeamId");
            
            //Declare team variable
            ITeam team = TeamFactory.createInstance();
            team.setName(super.getValue(request, "teamName"));
            //todo add all fields
            List<IEmployee> members = EmployeeFactory.createListInstance();
            for (int i=1; i<=Team.MEMBERS_COUNT; i++) {
                IEmployee e = EmployeeFactory.createInstance();
                //String str = request.getParameter("selectedMember"+i);
                int mId = getInteger(request, "selectedMember"+i);
                e.setId(mId);
                members.add(e);
            }
            team.setMembers(members);

            switch (action.toLowerCase()) {
                case "create":
                    team = service.createTeam(team);
                    team.setCreatedAt(new Date());
                    team.setIsDeleted(false);
                    request.setAttribute("team", team);
                    if(!service.isValid(team)) {
                        request.setAttribute("errors", team.getErrors());
                        request.setAttribute("employees", EmployeeServiceFactory.createInstance().getEmployees());
                        super.setView(request, TEAMS_MAINT_VIEW);
                    } else {
                        for(IEmployee e : team.getMembers()) {
                            IEmployee e2 = EmployeeServiceFactory.createInstance().getEmployee(e.getId());
                            e.setFirstName(e2.getFirstName());
                            e.setLastName(e2.getLastName());
                        }
                    }
                    break;
                case "save":
                    
                    break;
                case "delete":
                   
                    break;
            }
        } catch (Exception e) {
            super.setView(request, TEAMS_MAINT_VIEW);
            request.setAttribute("error", new ErrorViewModel("An error occurred attempting to maintain teams"));
            request.setAttribute("employees", EmployeeServiceFactory.createInstance().getEmployees());
        }

        super.getView().forward(request, response);
    }
}
