package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Đặt encoding UTF-8 để không bị lỗi font khi nhận dữ liệu
        request.setCharacterEncoding("UTF-8");
        
        // Lấy dữ liệu từ form (login.jsp)
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        // Kiểm tra thông tin qua DAO
        boolean isValid = userDAO.checkLogin(user, pass);

        if (isValid) {
            // Đăng nhập thành công: Lưu session và chuyển hướng tới trang chủ
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);
            response.sendRedirect("home.jsp"); // Đảm bảo bạn đã tạo trang home.jsp
        } else {
            // Đăng nhập thất bại: Gửi lại thông báo lỗi về trang login
            request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}