package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private final String jdbcUrl;
    private final String dbUser;
    private final String dbPass;

    public EmployeeDAO(String jdbcUrl, String dbUser, String dbPass) {
        this.jdbcUrl = jdbcUrl;
        this.dbUser = dbUser;
        this.dbPass = dbPass;
        
        try {
            // Load Driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Lỗi: Không tìm thấy Driver SQL Server!", ex);
        }
    }

    // 1. CHỨC NĂNG THÊM MỚI (CREATE)
    public boolean insert(Employee e) throws SQLException {
        String sql = "INSERT INTO EMPLOYEES (EMP_CODE, FULL_NAME, EMAIL, PHONE, GENDER, BIRTH_DATE, DEPT_ID, POSITION, SALARY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, e.getEmpCode());
            ps.setString(2, e.getFullName());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPhone());
            ps.setString(5, e.getGender());
            ps.setDate(6, Date.valueOf(e.getBirthDate()));
            ps.setInt(7, e.getDeptId());          // Truyền số nguyên int
            ps.setString(8, e.getPosition());
            ps.setBigDecimal(9, e.getSalary());   // Truyền BigDecimal
            
            return ps.executeUpdate() == 1;
        }
    }

    // 2. CHỨC NĂNG HIỂN THỊ DANH SÁCH & TÌM KIẾM (READ LIST)
    public void list(String search) throws SQLException {
        String sql = "SELECT e.EMP_CODE, e.FULL_NAME, e.EMAIL, d.DEPT_NAME, e.POSITION, e.SALARY " +
                     "FROM EMPLOYEES e LEFT JOIN DEPARTMENTS d ON e.DEPT_ID = d.DEPT_ID";
                     
        boolean useSearch = search != null && !search.trim().isEmpty();
        if (useSearch) {
            sql += " WHERE e.EMP_CODE LIKE ? OR e.FULL_NAME LIKE ?";
        }
        
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            if (useSearch) {
                String q = "%" + search + "%";
                ps.setString(1, q);
                ps.setString(2, q);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("------------------------------------------------------------------------------------------------------");
                System.out.printf("%-10s | %-20s | %-25s | %-20s | %-15s | %-15s%n", "Mã NV", "Họ Tên", "Email", "Phòng Ban", "Chức Vụ", "Lương");
                System.out.println("------------------------------------------------------------------------------------------------------");
                
                while (rs.next()) {
                    System.out.printf("%-10s | %-20s | %-25s | %-20s | %-15s | %-15.2f%n",
                            rs.getString("EMP_CODE"), 
                            rs.getString("FULL_NAME"), 
                            rs.getString("EMAIL"),
                            rs.getString("DEPT_NAME") != null ? rs.getString("DEPT_NAME") : "N/A", 
                            rs.getString("POSITION"), 
                            rs.getBigDecimal("SALARY"));
                }
                System.out.println("------------------------------------------------------------------------------------------------------");
            }
        }
    }

    // 3. CHỨC NĂNG XEM CHI TIẾT (READ DETAIL)
    public void detail(String code) throws SQLException {
        String sql = "SELECT e.*, d.DEPT_NAME FROM EMPLOYEES e LEFT JOIN DEPARTMENTS d ON e.DEPT_ID = d.DEPT_ID WHERE e.EMP_CODE = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\n--- CHI TIẾT NHÂN VIÊN ---");
                    System.out.println("Mã NV      : " + rs.getString("EMP_CODE"));
                    System.out.println("Họ tên     : " + rs.getString("FULL_NAME"));
                    System.out.println("Email      : " + rs.getString("EMAIL"));
                    System.out.println("Điện thoại : " + rs.getString("PHONE"));
                    System.out.println("Giới tính  : " + rs.getString("GENDER"));
                    System.out.println("Ngày sinh  : " + rs.getDate("BIRTH_DATE"));
                    System.out.println("Mã phòng   : " + rs.getInt("DEPT_ID") + " (" + rs.getString("DEPT_NAME") + ")");
                    System.out.println("Chức vụ    : " + rs.getString("POSITION"));
                    System.out.println("Lương      : " + rs.getBigDecimal("SALARY"));
                    System.out.println("--------------------------");
                } else {
                    System.out.println("=> Không tìm thấy nhân viên có mã: " + code);
                }
            }
        }
    }

    // 4. CHỨC NĂNG CẬP NHẬT (UPDATE)
    public boolean update(Employee e) throws SQLException {
        String sql = "UPDATE EMPLOYEES SET FULL_NAME=?, EMAIL=?, PHONE=?, GENDER=?, BIRTH_DATE=?, DEPT_ID=?, POSITION=?, SALARY=? WHERE EMP_CODE=?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, e.getFullName());
            ps.setString(2, e.getEmail());
            ps.setString(3, e.getPhone());
            ps.setString(4, e.getGender());
            ps.setDate(5, Date.valueOf(e.getBirthDate()));
            ps.setInt(6, e.getDeptId());
            ps.setString(7, e.getPosition());
            ps.setBigDecimal(8, e.getSalary());
            ps.setString(9, e.getEmpCode()); // Điều kiện WHERE
            
            return ps.executeUpdate() == 1;
        }
    }

    // 5. CHỨC NĂNG XÓA (DELETE)
    public boolean delete(String code) throws SQLException {
        String sql = "DELETE FROM EMPLOYEES WHERE EMP_CODE = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, code);
            return ps.executeUpdate() == 1;
        }
    }
}