package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.entity.ProductEntity;
import com.autoqa.qa2auto.service.ProductService;
import com.autoqa.qa2auto.service.impl.ProductServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/product")
public class ProductServletNew extends HttpServlet  {

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        try (PrintWriter out = resp.getWriter()) {
            List<ProductEntity> products = productService.findAll();
            out.write("<html><body>");
            out.write("<h2>Product List:</h2>");
            out.write("<ul>");
            for (ProductEntity product : products) {
                out.write("<li>" + product.getId() + " - " + product.getCode() + " - " + product.getName() + "</li>");
            }
            out.write("</ul>");
            out.write("</body></html>");
        }
    }
}
