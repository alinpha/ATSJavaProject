/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.repository.EmployeeRepoFactory;
import com.foxtrot.atssystem.repository.IEmployeeRepository;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveEmployee(IEmployee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public IEmployee getEmployee(int id) {
        return repo.retrieveEmployee(id);
        
    }

    @Override
    public List<IEmployee> getEmployees() {
         
       return repo.retrieveEmployees();
       
    }
    
}