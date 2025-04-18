/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isaac.EmployeeManagementApp.bean;

import com.isaac.EmployeeManagementApp.dao.EmployeeDAO;
import com.isaac.EmployeeManagementApp.model.Employee;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



/**
 *
 * @author Mad Titan
 */
@ManagedBean(name = "employeeBean")
@ViewScoped
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Employee> employees;
    private Employee selectedEmployee = new Employee();

    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    @PostConstruct
    public void init() {
        employees = employeeDAO.getAllEmployees();
    }

    public void addEmployee() {
        try {
            if (selectedEmployee != null) {
                employeeDAO.addEmployee(selectedEmployee);
                employees = employeeDAO.getAllEmployees();
                selectedEmployee = new Employee(); // reset form
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee() {
        try {
            if (selectedEmployee != null) {
                employeeDAO.updateEmployee(selectedEmployee);
                employees = employeeDAO.getAllEmployees();
                selectedEmployee = new Employee(); // reset form
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(Employee emp) {
        try {
            employeeDAO.deleteEmployee(emp.getId());
            employees = employeeDAO.getAllEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and Setters
    public List<Employee> getEmployees() {
        return employees;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }
}
