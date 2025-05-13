package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.TestGroupDto;
import com.autoqa.qa2auto.dto.UsersDto;
import com.autoqa.qa2auto.service.TestGroupService;
import com.autoqa.qa2auto.service.UsersService;
import com.autoqa.qa2auto.service.impl.TestGroupServiceImpl;
import com.autoqa.qa2auto.service.impl.UsersServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private final TestGroupService testGroupService = TestGroupServiceImpl.getInstance();
    private final UsersService usersService = UsersServiceImpl.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TestGroupDto> testGroups = testGroupService.findAll();
        req.setAttribute("testGroups", testGroups);

        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("userName");
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            Integer testGroupId = Integer.valueOf(req.getParameter("testGroupId"));

            var user = UsersDto.builder()
                    .username(username)
                    .email(email)
                    .password(password) // 🔐 здесь можно потом хешировать
                    .testGroupId(testGroupId)
                    .build();

            UsersDto savedUser = usersService.save(user);
            if (savedUser != null) {
                HttpSession session = req.getSession();
                session.setAttribute("registrationSuccess", "Пользователь " + username + " с email " + email + " успешно добавлен.");

                // Редирект на логин
                resp.sendRedirect(req.getContextPath() + "/login");
            } else {
                req.setAttribute("error", "Ошибка регистрации.");
                req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);


            }
        } catch (Exception e) {
            req.setAttribute("error", "Registration failed: " + e.getMessage());
            req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);
        }
    }

}
