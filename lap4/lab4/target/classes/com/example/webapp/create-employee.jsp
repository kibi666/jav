<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Thêm nhân viên</h2>
    <form action="${pageContext.request.contextPath}/employees/create" method="POST">
        <label>Mã nhân viên:</label>
        <input type="text" name="emp_code" pattern="^NV[0-9]{3,}$" title="Bắt đầu bằng NV + số" required><br><br>

        <label>Họ và tên:</label>
        <input type="text" name="full_name" required><br><br>

        <label>Email:</label>
        <input type="email" name="email" required><br><br>

        <button type="submit">Thêm</button>
        <a href="${pageContext.request.contextPath}/employees"><button type="button">Quay lại</button></a>
    </form>
</body>
</html>