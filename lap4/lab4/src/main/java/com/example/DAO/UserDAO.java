package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Model.User;

public class UserDAO {

    // 1. CREATE: Hàm thêm người dùng mới
    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (fullName, email, age, gender, major) VALUES (?, ?, ?, ?, ?)";
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getMajor());
            
            isSuccess = ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm: " + e.getMessage());
        }
        return isSuccess;
    }

    // 2. READ (List): Hàm lấy toàn bộ danh sách người dùng
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id")); // Lấy ID từ DB
                user.setFullName(rs.getString("fullName"));
                user.setEmail(rs.getString("email"));
                user.setAge(rs.getInt("age"));
                user.setGender(rs.getString("gender"));
                user.setMajor(rs.getString("major"));
                
                userList.add(user);
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách: " + e.getMessage());
        }
        return userList;
    }

    // 3. READ (Detail): Hàm lấy thông tin 1 người dùng dựa vào ID
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFullName(rs.getString("fullName"));
                    user.setEmail(rs.getString("email"));
                    user.setAge(rs.getInt("age"));
                    user.setGender(rs.getString("gender"));
                    user.setMajor(rs.getString("major"));
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy chi tiết: " + e.getMessage());
        }
        return user;
    }

    // 4. UPDATE: Hàm cập nhật thông tin người dùng
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET fullName = ?, email = ?, age = ?, gender = ?, major = ? WHERE id = ?";
        boolean isSuccess = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getGender());
            ps.setString(5, user.getMajor());
            ps.setInt(6, user.getId());

            isSuccess = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật: " + e.getMessage());
        }
        return isSuccess;
    }
}