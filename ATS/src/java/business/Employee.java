/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aline Vetrov
 */
public class Employee {
    
    //todo props , getters setters
    
    public List<Employee> getEmployees() {
        return new ArrayList<Employee>();
    }
    
    public Employee getEmployee(int id) {
        return new Employee();
    }
    
    public int saveEmployee() {
        return 0;
    }
    
    public int deleteEmployee(int id) {
        return 0;
    }
}
