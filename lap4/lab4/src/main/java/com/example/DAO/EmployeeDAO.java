package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.Employee;

public class EmployeeDAO {

    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEES_LAB4";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Employee(rs.getString("emp_code"), rs.getString("full_name"), rs.getString("email")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public Employee getByCode(String code) {
        String sql = "SELECT * FROM EMPLOYEES_LAB4 WHERE emp_code = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getString("emp_code"), rs.getString("full_name"), rs.getString("email"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public void insert(Employee emp) {
        String sql = "INSERT INTO EMPLOYEES_LAB4 (emp_code, full_name, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getEmpCode());
            ps.setString(2, emp.getFullName());
            ps.setString(3, emp.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void update(Employee emp) {
        String sql = "UPDATE EMPLOYEES_LAB4 SET full_name = ?, email = ? WHERE emp_code = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, emp.getFullName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getEmpCode());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(String code) {
        String sql = "DELETE FROM EMPLOYEES_LAB4 WHERE emp_code = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}