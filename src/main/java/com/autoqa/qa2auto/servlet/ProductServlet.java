package com.autoqa.qa2auto.servlet;

import com.autoqa.qa2auto.dto.ProductDto;
import com.autoqa.qa2auto.service.ProductService;
import com.autoqa.qa2auto.service.impl.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<ProductDto> productDto = productService.findAll();
        req.setAttribute("products", productDto);
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Product List</h2>");
            out.println("<ul>");
            for (ProductDto product : productDto) {
                out.printf("<li>%d - %s - %s</li>%n", product.getId(), product.getCode(), product.getName());
            }
            out.println("</ul>");
            out.println("</body></html>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        var parameterMap = req.getParameterMap();
//        System.out.println(parameterMap);
        try (var reader = req.getReader();
             var lines = reader.lines()) {
          lines.forEach(System.out::println);
        }
    }
}
