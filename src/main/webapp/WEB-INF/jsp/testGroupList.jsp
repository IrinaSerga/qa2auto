<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />
<body class="p-4 bg-light">
<div class="container">
  <h2 class="mb-4 text-center">Test Groups</h2>

  <table class="table table-striped table-hover">
    <thead>
    <tr>
      <th>#</th>
      <th>Group Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="group" items="${groups}" varStatus="loop">
      <tr>
        <td>${loop.index + 1}</td>
        <td>${group.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
