/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.ITask;
import com.foxtrot.atssystem.repository.EmployeeRepoFactory;
import com.foxtrot.atssystem.repository.IEmployeeRepository;
import com.foxtrot.atssystem.repository.TaskRepoFactory;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public class EmployeeService implements IEmployeeService {
    
    private final IEmployeeRepository repo;
    
    public EmployeeService() {
        repo = EmployeeRepoFactory.createInstance();
    }

    @Override
    public boolean isValid(IEmployee employee) {
        return employee.getErrors().isEmpty();
    }

    @Override
    public IEmployee createEmployee(IEmployee employee) {
        if(isValid(employee)) {
            employee.setId(repo.insertEmployee(employee));
        }
        
        return employee;
    }

    @Override
    public int saveEmployee(IEmployee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEmployee(int id) {
        return repo.deleteEmployee(id);
    }

    @Override
    public IEmployee getEmployee(int id) {
        
        IEmployee e = repo.retrieveEmployee(id);
        return e;
    }

    @Override
    public List<IEmployee> getEmployees() {
         
       return repo.retrieveEmployees();
       
    }
    
    @Override
    public List<IEmployee> getEmployeesInTeam(int teamId) {
         
       return repo.retrieveEmployeesInTeam(teamId);
       
    }
    
    @Override
    public int addEmployeeSkill(int eId, int sId) {
        if (eId <= 0 || sId <= 0) {
            return 0;
        }
        
        return repo.insertEmployeeSkill(eId, sId);
    }
    
    @Override
    public int removeEmployeeSkill(int eId, int sId) {
        if (eId <= 0 || sId <= 0) {
            return 0;
        }
        
        //check there are no future jobs require this skill
        //todo after job dev completed
        
        return repo.insertEmployeeSkill(eId, sId);
    }
    
    @Override
    public List<IEmployee> getEmployeesBySin(String sin) {
         
       return repo.retrieveEmployeesBySin(sin);
       
    }
    
    @Override
    public List<IEmployee> getEmployeesByLastName(String lastName) {
         
       return repo.retrieveEmployeesByLastName(lastName);
       
    }
    
}
