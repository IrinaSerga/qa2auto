<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Subsystems</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
  <h2 class="mb-4 text-primary">Список подсистем</h2>
  <table class="table table-striped">
    <thead>
    <tr>
      <th>Название</th>
      <th>Действие</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="subsystem" items="${subsystems}">
      <tr>
        <td>${subsystem.name}</td>
        <td>
          <a href="${pageContext.request.contextPath}/subsystems?id=${subsystem.id}" class="btn btn-sm btn-outline-primary">Открыть</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
