package com.isaac.EmployeeManagementApp.dao;

import com.isaac.EmployeeManagementApp.model.Employee;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

public class EmployeeDAO {
    @Resource(name="jdbc/employeedb")
    private DataSource dataSource;

    public EmployeeDAO() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/employeedb");
        } catch (NamingException e) {
            throw new RuntimeException("JNDI lookup failed for DataSource", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void addEmployee(Employee emp) {
        String sql = "INSERT INTO employees (first_name, last_name, email, department, salary) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getDepartment());
            ps.setDouble(5, emp.getSalary());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee emp) {
        String sql = "UPDATE employees SET first_name=?, last_name=?, email=?, department=?, salary=? WHERE id=?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getDepartment());
            ps.setDouble(5, emp.getSalary());
            ps.setInt(6, emp.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Database connection successful!");
            } else {
                System.out.println("Connection failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error while connecting to DB: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
