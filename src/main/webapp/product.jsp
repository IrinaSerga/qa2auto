<%@ page import="com.autoqa.qa2auto.dto.ProductDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.autoqa.qa2auto.service.impl.ProductServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="https://mycompany.com" %>--%>

<html>
<head>
    <title>Hello JSP</title></head>
<body>

<h2>Product List</h2>
<ul>
    <%
        List<ProductDto> products = ProductServiceImpl.getInstance().findAll();
        for (ProductDto product : products) {
            out.write(String.format("<li>%s</li>", product.getName()));
        }
    %>
</ul>
</body>
</html>


<%--
<%@ page import="com.autoqa.qa2auto.dto.ProductDto" %>
<%@ page import="java.util.List" %>
<%@ page import="com.autoqa.qa2auto.service.impl.ProductServiceImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4 text-primary">Product List</h2>
    <ul class="list-group">
        <%
            List<ProductDto> products = ProductServiceImpl.getInstance().findAll();
            for (ProductDto product : products) {
                out.write(String.format("<li class=\"list-group-item\">%s</li>", product.getName()));
            }
        %>
    </ul>
</div>
</body>
</html>
--%>
