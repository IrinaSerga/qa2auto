<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Hello JSP</title>
</head>
<body>

<h2>Product List</h2>
<ul>
    <c:forEach var="product" items="${products}">
        <li>${product.name}</li>
    </c:forEach>
</ul>

</body>
</html>
