<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Kết Quả Đăng Ký</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .container {
            max-width: 500px;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        h2 {
            color: #333;
        }
        .info-group {
            margin-bottom: 15px;
            font-size: 16px;
        }
        .label {
            font-weight: bold;
            display: inline-block;
            width: 120px;
        }
        .back-btn {
            margin-top: 15px;
            display: inline-block;
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Thông Tin Đăng Ký Thành Công</h2>
        <hr>
        
        <div class="info-group">
            <span class="label">Họ và tên:</span> 
            <span>${requestScope.fullName}</span>
        </div>
        
        <div class="info-group">
            <span class="label">Email:</span> 
            <span>${requestScope.email}</span>
        </div>
        
        <div class="info-group">
            <span class="label">Tuổi:</span> 
            <span>${requestScope.age}</span>
        </div>
        
        <div class="info-group">
            <span class="label">Giới tính:</span> 
            <span>${requestScope.gender}</span>
        </div>
        
        <div class="info-group">
            <span class="label">Chuyên ngành:</span> 
            <span>${requestScope.major}</span>
        </div>

        <a href="/register.jsp" class="back-btn">Quay lại Form</a>
    </div>

</body>
</html>