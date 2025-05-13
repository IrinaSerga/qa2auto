package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.service.TestGroupService;
import com.autoqa.qa2auto.service.impl.TestGroupServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/group")
public class TestGroupServlet extends HttpServlet {

    private final TestGroupService testGroupService = TestGroupServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var groups = testGroupService.findAll();
        req.setAttribute("groups", groups);
        req.getRequestDispatcher(JspHelper.getPath("testGroupList"))
                .forward(req, resp);
    }
}
