<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Danh sách nhân viên</h2>
    <a href="${pageContext.request.contextPath}/employees/create"><button>Thêm mới</button></a>
    <br><br>
    <table border="1" cellpadding="8" style="border-collapse: collapse;">
        <tr>
            <th>Code</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="emp" items="${empList}">
            <tr>
                <td>${emp.empCode}</td>
                <td>${emp.fullName}</td>
                <td>${emp.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/employees/view?code=${emp.empCode}">View</a> | 
                    <a href="${pageContext.request.contextPath}/employees/edit?code=${emp.empCode}">Edit</a> | 
                    
                    <form action="${pageContext.request.contextPath}/employees/delete" method="POST" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa nhân viên này?');">
                        <input type="hidden" name="emp_code" value="${emp.empCode}">
                        <button type="submit" style="background:none; border:none; color:blue; cursor:pointer; text-decoration:underline;">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>