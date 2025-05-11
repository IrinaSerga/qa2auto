package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.ProductDto;
import com.autoqa.qa2auto.service.ProductService;
import com.autoqa.qa2auto.service.impl.ProductServiceImpl;
import com.autoqa.qa2auto.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDto> productDto = productService.findAll();
        req.setAttribute("products", productDto);
        req.getRequestDispatcher(JspHelper.getPath("product"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var reader = req.getReader();
             var lines = reader.lines()) {
            lines.forEach(System.out::println);
        }
    }
}
