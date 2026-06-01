<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng Nhập</title>
    <style>
        body { font-family: Arial, sans-serif; display: flex; justify-content: center; margin-top: 50px; }
        .login-box { border: 1px solid #ccc; padding: 20px; width: 300px; box-shadow: 0 0 10px rgba(0,0,0,0.1); }
        .error { color: #721c24; background-color: #f8d7da; padding: 10px; margin-bottom: 15px; border-radius: 4px; font-size: 14px;}
        input[type="text"], input[type="password"] { width: 93%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; }
        button { width: 100%; padding: 10px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="login-box">
        <h2 style="text-align: center;">Đăng Nhập</h2>
        
        <%-- Hiển thị thông báo lỗi nếu có --%>
        <% if(request.getAttribute("errorMessage") != null) { %>
            <div class="error"><%= request.getAttribute("errorMessage") %></div>
        <% } %>

        <form action="LoginServlet" method="post">
            <label>Tên đăng nhập</label>
            <input type="text" name="username" value="admin" required>
            
            <label>Mật khẩu</label>
            <input type="password" name="password" value="123456" required>
            
            <button type="submit">Đăng nhập</button>
        </form>
    </div>
</body>
</html>
