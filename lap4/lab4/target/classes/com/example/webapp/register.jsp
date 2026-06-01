<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Form Đăng Ký Người Dùng</title>
    <style>
        .error { color: red; font-size: 0.9em; }
    </style>
</head>
<body>
    <h2>Form Đăng Ký Người Dùng</h2>
    <form action="RegistrationServlet" method="POST">
        <label>Họ tên:</label>
        <input type="text" name="fullName" required>
        <c:if test="${not empty errors.fullName}">
            <div class="error">${errors.fullName}</div>
        </c:if>
        <br><br>

        <label>Email:</label>
        <input type="email" name="email" required>
        <c:if test="${not empty errors.email}">
            <div class="error">${errors.email}</div>
        </c:if>
        <br><br>

        <label>Tuổi:</label>
        <input type="number" name="age" min="18" max="60" required>
        <c:if test="${not empty errors.age}">
            <div class="error">${errors.age}</div>
        </c:if>
        <br><br>

        <label>Giới tính:</label>
        <input type="radio" name="gender" value="Nam" checked> Nam
        <input type="radio" name="gender" value="Nữ"> Nữ
        <br><br>

        <label>Chuyên ngành:</label>
        <select name="major">
            <option value="Công nghệ thông tin">Công nghệ thông tin</option>
            <option value="Kinh doanh quốc tế">Kinh doanh quốc tế</option>
            <option value="Ngôn ngữ Anh">Ngôn ngữ Anh</option>
        </select>
        <br><br>

        <button type="submit">Đăng ký</button>
    </form>
</body>
</html>