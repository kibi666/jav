package com.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static final String SERVER_NAME = "localhost";
    private static final String PORT_NUMBER = "1433";
    private static final String DATABASE_NAME = "LAB3"; 
    private static final String USERNAME = "sa";        
    private static final String PASSWORD = "123";     
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Khai báo Driver cho SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Tạo chuỗi kết nối (Thêm encrypt=false để tránh lỗi chứng chỉ SSL trên máy local)
            String dbUrl = "jdbc:sqlserver://" + SERVER_NAME + ":" + PORT_NUMBER 
                         + ";databaseName=" + DATABASE_NAME 
                         + ";encrypt=false;";
            
            // Thực hiện kết nối
            conn = DriverManager.getConnection(dbUrl, USERNAME, PASSWORD);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
            
        } catch (ClassNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy Driver JDBC cho SQL Server!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Lỗi: Không thể kết nối đến cơ sở dữ liệu!");
            e.printStackTrace();
        }
        return conn;
    }

    // Hàm main để chạy test thử xem kết nối được chưa (không cần gọi trên Servlet)
    public static void main(String[] args) {
        getConnection();
    }
}