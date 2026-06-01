package com.example.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.DAO.EmployeeDAO;
import com.example.Model.Employee;

// Khai báo mảng URL mapping để bắt mọi request liên quan đến employees
@WebServlet({"/employees", "/employees/create", "/employees/view", "/employees/edit", "/employees/update", "/employees/delete"})
public class EmployeeServlet extends HttpServlet {
    
    private EmployeeDAO dao = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath(); // Lấy đường dẫn URL hiện tại

        switch (path) {
            case "/employees/create":
                request.getRequestDispatcher("/create_employee.jsp").forward(request, response);
                break;
                
            case "/employees/view":
                String viewCode = request.getParameter("code");
                request.setAttribute("employee", dao.getByCode(viewCode));
                request.getRequestDispatcher("/view_employee.jsp").forward(request, response);
                break;
                
            case "/employees/edit":
                String editCode = request.getParameter("code");
                request.setAttribute("employee", dao.getByCode(editCode));
                request.getRequestDispatcher("/edit_employee.jsp").forward(request, response);
                break;
                
            default: // Mặc định là /employees (List)
                List<Employee> list = dao.getAll();
                request.setAttribute("empList", list);
                request.getRequestDispatcher("/list_employees.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();

        if ("/employees/create".equals(path)) {
            Employee emp = new Employee(
                request.getParameter("emp_code"),
                request.getParameter("full_name"),
                request.getParameter("email")
            );
            dao.insert(emp);
            response.sendRedirect(request.getContextPath() + "/employees");

        } else if ("/employees/update".equals(path)) {
            Employee emp = new Employee(
                request.getParameter("emp_code"), // Emp_code readonly từ form
                request.getParameter("full_name"),
                request.getParameter("email")
            );
            dao.update(emp);
            response.sendRedirect(request.getContextPath() + "/employees");

        } else if ("/employees/delete".equals(path)) {
            String code = request.getParameter("emp_code");
            dao.delete(code);
            response.sendRedirect(request.getContextPath() + "/employees");
        }
    }
}