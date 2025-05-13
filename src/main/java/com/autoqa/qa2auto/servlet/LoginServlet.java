package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.UsersDto;
import com.autoqa.qa2auto.service.UsersService;
import com.autoqa.qa2auto.service.impl.UsersServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UsersService usersService = UsersServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<UsersDto> user = usersService.findByUsernameAndEmail(username, email);

        if (user.isPresent() && usersService.checkPassword(user.get(), password)) {
            req.getSession().setAttribute("user", user.get());
            resp.sendRedirect(req.getContextPath() + "/product");
        } else {
            req.setAttribute("error", "Неверное имя пользователя, email или пароль");
            req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
        }
    }
}
