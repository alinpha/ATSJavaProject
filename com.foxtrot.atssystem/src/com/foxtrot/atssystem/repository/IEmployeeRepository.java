/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.atssystem.models.IEmployee;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public interface IEmployeeRepository {
    int insertEmployee(IEmployee employee);
    int updateEmployee(IEmployee employee);
    int deleteEmployee(int id);
    List<IEmployee> retrieveEmployees();
    IEmployee retrieveEmployee(int id);   
    int insertEmployeeSkill(int empId, int taskId);
    List<IEmployee> retrieveEmployeesBySin(String sin);
    List<IEmployee> retrieveEmployeesByLastName(String lastName);
}