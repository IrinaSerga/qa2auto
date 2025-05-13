<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-12" style="max-width: 500px;">
            <div class="card shadow p-4 mx-auto">
                <h3 class="mb-4 text-center">
                    <i class="bi bi-person-plus-fill me-2"></i>Create User
                </h3>

                <c:if test="${not empty registrationSuccess}">
                    <div class="alert alert-success text-center">
                            ${registrationSuccess}
                    </div>
                    <script>
                        setTimeout(function () {
                            window.location.href = '${pageContext.request.contextPath}/login';
                        }, 3000);
                    </script>
                </c:if>

                <form action="${pageContext.request.contextPath}/registration" method="post" autocomplete="off">
                    <div class="mb-3">
                        <label for="userId" class="form-label">User Name:</label>
                        <input type="text" class="form-control" name="userName" id="userId" required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label">Email:</label>
                        <input type="email" class="form-control" name="email" id="email" required>
                    </div>

                    <div class="mb-4">
                        <label for="password" class="form-label">Password:</label>
                        <input type="password" class="form-control" name="password" id="password" required>
                    </div>

                    <div class="mb-3">
                        <label for="testGroupId" class="form-label">Test Group:</label>
                        <select class="form-select" name="testGroupId" id="testGroupId" required>
                            <option value="" disabled selected>Select a group</option>
                            <c:forEach var="group" items="${testGroups}">
                                <option value="${group.id}">${group.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Register</button>
                    </div>

                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp"/>
