/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.nbcc.dataaccess.DALFactory;
import com.nbcc.dataaccess.IDAL;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.EmployeeFactory;
import java.sql.SQLException;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Aline Vetrov
 */
public class EmployeeRepository extends BaseRepository implements IEmployeeRepository {
    
    private final String SPROC_SELECT_INVOICES = "CALL selectEmployees(null);";
    
    private IDAL dataAccess;
    
    public EmployeeRepository() {
        dataAccess = DALFactory.createInstance();
    }

    @Override
    public int insertEmployee(IEmployee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateEmployee(IEmployee employee) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deleteEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IEmployee> retrieveEmployees() {
        List<IEmployee> list = EmployeeFactory.createListInstance();
        try {
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_INVOICES, null);
            list = toListOfEmployees(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    @Override
    public IEmployee retrieveEmployee(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * map cached rowset to employee list
     * @param rs
     * @return
     * @throws SQLException 
     */
    private List<IEmployee> toListOfEmployees(CachedRowSet rs) throws SQLException {
        List<IEmployee> list = EmployeeFactory.createListInstance();
        IEmployee emp;
        while(rs.next()) {
            emp = EmployeeFactory.createInstance();
            emp.setId(super.getInt("id", rs));
            emp.setFirstName(rs.getString("firstName"));
            emp.setLastName(rs.getString("lastName"));
            emp.setSIN(rs.getString("sin"));
            emp.setHourlyRate(super.getDouble("hourlyRate", rs));
            emp.setIsDeleted(rs.getBoolean("isDeleted"));
            emp.setCreatedAt(rs.getDate("createdAt"));
            emp.setUpdatedAt(rs.getDate("updatedAt"));
            emp.setDeletedAt(rs.getDate("deletedAt"));
            list.add(emp);
        }
        
        return list;
    }
    
}
