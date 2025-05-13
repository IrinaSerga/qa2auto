<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/common/header.jsp" />

<div class="container">
    <h2 class="mb-4 text-center">Users List</h2>

    <table class="table table-striped table-hover">
        <thead class="table-primary">
        <tr>
            <th>#</th>
            <th>Username</th>
            <th>Email</th>
            <th>Test Group</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="users" items="${users}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${users.username}</td>
                <td>${users.email}</td>
                <td>${users.testGroupName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />
