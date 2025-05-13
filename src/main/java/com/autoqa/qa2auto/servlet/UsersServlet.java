package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.service.UsersService;
import com.autoqa.qa2auto.service.impl.UsersServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private final UsersService usersService = UsersServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", usersService.findAll());
        req.getRequestDispatcher(JspHelper.getPath("users")).forward(req, resp);
    }
}
