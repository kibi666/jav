package com.example.Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Util.ValidationUtil;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");

        // Lấy dữ liệu từ form
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String major = request.getParameter("major");

        Map<String, String> errors = new HashMap<>();

        // Validate Họ tên
        if (fullName == null || fullName.trim().isEmpty()) {
            errors.put("fullName", "Họ tên không được để trống hoặc chỉ có khoảng trắng.");
        }

        // Validate Email
        if (email == null || !ValidationUtil.isValidEmail(email)) {
            errors.put("email", "Email không đúng định dạng (phải chứa @).");
        }

        // Validate Tuổi
        try {
            int age = Integer.parseInt(ageStr);
            if (age < 18 || age > 60) {
                errors.put("age", "Tuổi phải nằm trong khoảng từ 18 đến 60.");
            }
        } catch (NumberFormatException e) {
            errors.put("age", "Tuổi phải là số.");
        }

        // Xử lý luồng
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } else {
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("age", ageStr);
            request.setAttribute("gender", gender);
            request.setAttribute("major", major);
            request.getRequestDispatcher("/result.jsp").forward(request, response);
        }
    }
}