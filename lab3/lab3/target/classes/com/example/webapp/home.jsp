<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang Chủ</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; margin-top: 50px; }
        .home-box { border: 1px solid #ccc; padding: 20px; width: 300px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .success { color: #155724; background-color: #d4edda; padding: 10px; margin-bottom: 15px; border-radius: 4px; font-size: 14px;}
        a { display: block; margin-top: 15px; text-align: center; padding: 10px; background-color: #dc3545; color: white; text-decoration: none; border-radius: 4px; }
        a:hover { background-color: #c82333; }
    </style>
</head>
<body>
    <div class="home-box">
        <h2 style="text-align: center;">Trang Chủ</h2>
        
        <%
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            if (loggedInUser != null) {
        %>
            <div class="success">
                Xin chào, <strong><%= loggedInUser %></strong>! Bạn đã đăng nhập thành công.
            </div>
            <a href="logout.jsp">Đăng Xuất</a>
        <%
            } else {
        %>
            <div class="error">
                Bạn chưa đăng nhập. <a href="login.jsp">Quay lại đăng nhập</a>
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
