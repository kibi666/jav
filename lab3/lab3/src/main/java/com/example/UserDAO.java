package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    // Cấu hình CSDL SQL Server của bạn
    private String jdbcUrl = "jdbc:sqlserver://localhost:1433;databaseName=LAB3;encrypt=true;trustServerCertificate=true;";
    private String dbUser = "sa";
    private String dbPass = "12345"; 

    public UserDAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.setString(2, password);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu có kết quả (đăng nhập thành công)
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}