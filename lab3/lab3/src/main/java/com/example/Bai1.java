package com.example;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bai1 {
    // Nhớ cấu hình tài khoản java_user và mật khẩu của bạn
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=LAB3;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASS = "12345"; 

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Hệ thống không hỗ trợ bảng mã UTF-8");
        }
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            System.out.println("=== DANH SÁCH NHÂN VIÊN (CÓ TÊN PHÒNG BAN) ===");
            // Dùng JOIN để nối bảng EMPLOYEES và DEPARTMENTS
            String sql1 = "SELECT e.EMP_CODE, e.FULL_NAME, e.SALARY, d.DEPT_NAME " +
                          "FROM EMPLOYEES e LEFT JOIN DEPARTMENTS d ON e.DEPT_ID = d.DEPT_ID";
            ResultSet rs1 = stmt.executeQuery(sql1);
            
            while (rs1.next()) {
                String code = rs1.getString("EMP_CODE");
                String name = rs1.getString("FULL_NAME");
                double salary = rs1.getDouble("SALARY");
                String deptName = rs1.getString("DEPT_NAME");
                
                System.out.printf("Mã: %s | Tên: %s | Lương: %.2f | Phòng: %s%n", 
                                  code, name, salary, deptName != null ? deptName : "Chưa xếp phòng");
            }
            rs1.close();

            System.out.println("\n=== THỐNG KÊ PHÒNG BAN (>= 2 NHÂN VIÊN) ===");
            // Group by kết hợp JOIN để đếm số nhân viên
            String sql2 = "SELECT d.DEPT_NAME, COUNT(e.EMP_ID) as NUM_EMP " +
                          "FROM EMPLOYEES e JOIN DEPARTMENTS d ON e.DEPT_ID = d.DEPT_ID " +
                          "GROUP BY d.DEPT_NAME " +
                          "HAVING COUNT(e.EMP_ID) >= 2";
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                System.out.printf("Phòng ban: %s | Số lượng nhân viên: %d%n", 
                                  rs2.getString("DEPT_NAME"), rs2.getInt("NUM_EMP"));
            }
            rs2.close();

        } catch (SQLException e) {
            System.err.println("Lỗi CSDL: " + e.getMessage());
        }
    }
}