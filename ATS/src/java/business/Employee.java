/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Aline Vetrov
 */
public class Employee implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String sin;
    private double hourlyRate;
    private boolean isDeleted;
    private Date createdAt;
    private Date updateddAt;
    private Date deleteddAt;
    
    private List<Employee> mockupData;// = new ArrayList();
    
    private void buildMockData() {
        this.mockupData = new ArrayList();
        Date now = new Date();
        this.mockupData.add(new Employee(mockupData.size()+1, "Bob", "Marley", "123456789", 50, false, now, null, null));
        this.mockupData.add(new Employee(mockupData.size()+1, "Endy", "White", "123456789", 50, false, now, null, null));
        this.mockupData.add(new Employee(mockupData.size()+1, "Jane", "Doe", "123456789", 50, false, now, null, null));
    }
    
    public List<Employee> getEmployees() {
        buildMockData();
        return this.mockupData;
    }
    
    public Employee getEmployee(int id) {
        buildMockData();
        List<Employee> query = mockupData.stream().filter(b -> b.getId() == id)
                    .collect(Collectors.toList());
        
        if(query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }
    
    public Employee create() {
        this.id = this.mockupData.size() + 1;
        return this;
    }
    
    public int saveEmployee() {
        buildMockData();
        return 1;
    }
    
    public int deleteEmployee(int id) {
        return 1;
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Employee() {
        
    }
    
    public Employee(int id, String fname, String lname, String sin, double rate, boolean isDeleted, Date created, Date updated, Date deleted) {
        this.id = id;
        this.firstName = fname;
        this.lastName = lname;
        this.sin = sin;
        this.hourlyRate = rate;
        this.isDeleted = isDeleted;
        this.createdAt = created;
        this.updateddAt = updated;
        this.deleteddAt = deleted;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getters/setters">
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSin() {
        return sin;
    }

    public void setSin(String sin) {
        this.sin = sin;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateddAt() {
        return updateddAt;
    }

    public void setUpdateddAt(Date updateddAt) {
        this.updateddAt = updateddAt;
    }

    public Date getDeleteddAt() {
        return deleteddAt;
    }

    public void setDeleteddAt(Date deleteddAt) {
        this.deleteddAt = deleteddAt;
    }

    public List<Employee> getMockupData() {
        return mockupData;
    }

    public void setMockupData(List<Employee> mockupData) {
        this.mockupData = mockupData;
    }
    
//</editor-fold>

    
}
