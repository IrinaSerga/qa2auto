<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Subsystem Info</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4 text-success">Информация о подсистеме</h2>

  <c:if test="${not empty subsystem}">
    <ul class="list-group">
      <li class="list-group-item"><strong>ID:</strong> ${subsystem.id}</li>
      <li class="list-group-item"><strong>Название:</strong> ${subsystem.name}</li>
      <li class="list-group-item"><strong>Product ID:</strong> ${subsystem.productId}</li>
      <li class="list-group-item"><strong>Test Group ID:</strong> ${subsystem.testGroupId}</li>
    </ul>
    <a href="${pageContext.request.contextPath}/subsystems" class="btn btn-secondary mt-3">Назад</a>
  </c:if>

  <c:if test="${empty subsystem}">
    <div class="alert alert-danger">Подсистема не найдена.</div>
  </c:if>
</div>
</body>
</html>
