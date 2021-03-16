/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.business;

import com.foxtrot.atssystem.models.IEmployee;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public interface IEmployeeService {
    boolean isValid(IEmployee employee);
    IEmployee createEmployee(IEmployee employee);
    int saveEmployee(IEmployee employee);
    int deleteEmployee(int id);
    
    IEmployee getEmployee(int id);
    List<IEmployee> getEmployees();
}
