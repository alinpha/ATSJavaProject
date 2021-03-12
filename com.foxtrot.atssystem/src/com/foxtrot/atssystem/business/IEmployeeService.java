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
 * @description The Employee Service is responsible for business rules, crud methods for the Employee Model
 */
public interface IEmployeeService {
    void calculateTotal(IEmployee invoice);
    boolean isValid(IEmployee invoice);
    
    IEmployee createEmployee(IEmployee employee);
    int saveInvoice(IEmployee employee);
    int deleteInvoice(int id);
    
    IEmployee getEmployee(int id);
    List<IEmployee> getEmployees();
    
}
