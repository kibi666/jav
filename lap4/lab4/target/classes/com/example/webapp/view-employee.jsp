<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Chi tiết nhân viên</h2>
    <p><b>Mã:</b> ${employee.empCode}</p>
    <p><b>Họ tên:</b> ${employee.fullName}</p>
    <p><b>Email:</b> ${employee.email}</p>
    
    <a href="${pageContext.request.contextPath}/employees"><button>Quay lại</button></a>
</body>
</html>