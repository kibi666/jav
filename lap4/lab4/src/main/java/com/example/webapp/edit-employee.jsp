<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Sửa nhân viên</h2>
    <form action="${pageContext.request.contextPath}/employees/update" method="POST">
        <label>Mã nhân viên:</label>
        <input type="text" name="emp_code" value="${employee.empCode}" readonly style="background:#eee;"><br><br>

        <label>Họ và tên:</label>
        <input type="text" name="full_name" value="${employee.fullName}" required><br><br>

        <label>Email:</label>
        <input type="email" name="email" value="${employee.email}" required><br><br>

        <button type="submit">Lưu</button>
        <a href="${pageContext.request.contextPath}/employees"><button type="button">Quay lại</button></a>
    </form>
</body>
</html>