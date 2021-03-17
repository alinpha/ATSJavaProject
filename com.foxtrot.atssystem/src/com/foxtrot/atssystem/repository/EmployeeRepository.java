/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foxtrot.atssystem.repository;

import com.foxtrot.dataaccess.DALFactory;
import com.foxtrot.dataaccess.IDAL;
import com.foxtrot.atssystem.models.IEmployee;
import com.foxtrot.atssystem.models.EmployeeFactory;
import com.foxtrot.dataaccess.IParameter;
import com.foxtrot.dataaccess.ParameterFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author Aline Vetrov
 */
public class EmployeeRepository extends BaseRepository implements IEmployeeRepository {
    
    private final String SPROC_SELECT_EMPLOYEES = "CALL selectemployees(null);";
    private final String SPROC_SELECT_EMPLOYEE = "CALL selectemployees(?);";
    private final String SPROC_INSERT_EMPLOYEE = "CALL insertemployee(?,?,?,?,?);";
    
    private IDAL dataAccess;
    
    public EmployeeRepository() {
        dataAccess = DALFactory.createInstance();
    }

    @Override
    public int insertEmployee(IEmployee employee) {
        int id = 0;
        
        try {
            List<IParameter> params = new ArrayList();
            params.add(ParameterFactory.creteInstance(employee.getFirstName()));
            params.add(ParameterFactory.creteInstance(employee.getLastName()));
            params.add(ParameterFactory.creteInstance(employee.getSin()));
            params.add(ParameterFactory.creteInstance(employee.getHourlyRate()));
            
            params.add(ParameterFactory.creteInstance(id, IParameter.Direction.OUT, java.sql.Types.INTEGER));
      
            List<Object> returnedValues = dataAccess.executeNonQuery(SPROC_INSERT_EMPLOYEE, params);
            
            if(returnedValues != null) {
              id = Integer.parseInt(returnedValues.get(0).toString());
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
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
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_EMPLOYEES, null);
            list = toListOfEmployees(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //@Override
    public IEmployee retrieveEmployee(int id) {
        List<IEmployee> list = EmployeeFactory.createListInstance();
        
        try {
            List<IParameter> params = ParameterFactory.createListInstance();
            params.add(ParameterFactory.creteInstance(id));
            CachedRowSet rs = dataAccess.executeFill(SPROC_SELECT_EMPLOYEE, params);
            list = toListOfEmployees(rs);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    /**
     * map cached rowset to employees list
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
            emp.setSin(rs.getString("sin"));
            emp.setHourlyRate(rs.getDouble("hourlyRate"));
            emp.setIsDeleted(rs.getBoolean("isDeleted"));
            emp.setCreatedAt(rs.getDate("createdAt"));
            emp.setUpdatedAt(rs.getDate("updatedAt"));
            emp.setDeletedAt(rs.getDate("deletedAt"));
            //inv.setInvoiceDescription(rs.getString("InvoiceDescription"));
            //inv.setSubtotal(super.getDouble("InvoiceAmount", rs));
            //inv.setInvoiceDate(super.getDate("InvoiceDate", rs));
            list.add(emp);
        }
        
        return list;
    }
    
}
