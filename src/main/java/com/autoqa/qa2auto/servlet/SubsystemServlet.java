package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.SubsystemDto;
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

@WebServlet("/subsystems")
public class SubsystemServlet extends HttpServlet {

    private final SubsystemServiceImpl subsystemService = SubsystemServiceImpl.getInstance();

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


      /*  String idParam = req.getParameter("id");

        if (idParam != null) {
            try {
                Integer id = Integer.parseInt(idParam);
                SubsystemDto subsystem = subsystemService.findById(id);
                req.setAttribute("subsystem", subsystem);
                req.getRequestDispatcher(JspHelper.getPath("subsystemSingle"))
                        .forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format");
            } catch (RuntimeException e) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
            }
        } else {
            List<SubsystemDto> subsystems = subsystemService.findAll();
            req.setAttribute("subsystems", subsystems);
            req.getRequestDispatcher(JspHelper.getPath("subsystemList"))
                    .forward(req, resp);
        }*/

