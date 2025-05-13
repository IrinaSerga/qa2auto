<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
  <meta charset="UTF-8">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
  <div class="row justify-content-center">
    <div class="col-12 col-md-6 col-lg-5">
      <div class="card shadow p-4">
        <h3 class="text-center mb-4">Login</h3>

        <!-- Ошибка входа -->
        <c:if test="${not empty error}">
          <div class="alert alert-danger">
              ${error}
          </div>
          <c:remove var="error" scope="request"/>
        </c:if>

        <!-- Форма входа -->
        <form action="${pageContext.request.contextPath}/login" method="post" onsubmit="return validateLoginForm()" autocomplete="off">
          <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" name="username" id="username" required>
          </div>

          <div class="mb-3">
            <label for="password" class="form-label">Password</label>
            <input type="password" class="form-control" name="password" id="password" required>
          </div>

          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" name="email" id="email" required>
          </div>

          <div class="d-grid">
            <button type="submit" class="btn btn-primary">Login</button>
          </div>
        </form>

      </div>
    </div>
  </div>
</div>

<script>
  function validateLoginForm() {
    const usernameInput = document.getElementById("username");
    const passwordInput = document.getElementById("password");
    const emailInput = document.getElementById("email");

    const username = usernameInput.value.trim().replace(/[\u0000-\u001F\u007F]/g, "");
    const password = passwordInput.value.trim().replace(/[\u0000-\u001F\u007F]/g, "");
    const email = emailInput.value.trim().replace(/[\u0000-\u001F\u007F]/g, "");

    usernameInput.value = username;
    passwordInput.value = password;
    emailInput.value = email;

    if (!username || !password || !email) {
      alert("Пожалуйста, заполните все поля корректно.");
      return false;
    }
    return true;
  }
</script>

<jsp:include page="/WEB-INF/jsp/common/footer.jsp" />

</body>
</html>
