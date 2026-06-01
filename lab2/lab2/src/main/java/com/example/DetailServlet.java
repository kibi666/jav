package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
                throws ServletException, IOException {
        Item item = new Item("Nokia 2020", "nokia.png", 500.0, 0.0, 1);
        req.setAttribute("item", item);
        req.getRequestDispatcher("/view/item/detail.jsp").forward(req, resp);
    }
}
