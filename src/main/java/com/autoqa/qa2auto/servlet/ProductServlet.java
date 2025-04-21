package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dao.impl.ProductDaoImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private final ProductDaoImpl productDao = new ProductDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            resp.setContentType("text/html");
            try (PrintWriter out = resp.getWriter()) {
                out.write("Hello from first servlet");
            }
        }
}
