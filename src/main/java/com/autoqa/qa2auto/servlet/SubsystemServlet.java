package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.SubsystemDto;
import com.autoqa.qa2auto.service.SubsystemService;
import com.autoqa.qa2auto.service.impl.SubsystemServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/subsystem")
public class SubsystemServlet extends HttpServlet {

    private final SubsystemService subsystemService = SubsystemServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SubsystemDto> subsystems = subsystemService.findAll();
        req.setAttribute("subsystems", subsystems);
        req.getSession().setAttribute("subsystemsMap", subsystems.stream()
                .collect(Collectors.toMap(SubsystemDto::getId, SubsystemDto::getName)));
        req.getRequestDispatcher(JspHelper.getPath("subsystemList"))
                .forward(req, resp);

    }
}
