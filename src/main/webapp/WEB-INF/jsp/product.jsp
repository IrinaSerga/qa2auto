<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .accordion-button {
            font-weight: bold; /* жирный заголовок продукта */
        }

        .subsystem-list .list-group-item {
            background-color: #f8f9fa;
            font-weight: 500;
            padding: 0.75rem 1.25rem;
        }

        .accordion-body {
            padding: 1rem 1.5rem;
        }

        .wrapper {
            max-width: 800px;
            margin: auto;
        }
    </style>
</head>
<body class="p-4">
<div class="wrapper">
    <h2 class="mb-4 text-center">📦 Product List</h2>

    <div class="accordion" id="productAccordion">
        <c:forEach var="product" items="${products}" varStatus="status">
            <div class="accordion-item mb-2 shadow-sm">
                <h2 class="accordion-header" id="heading${status.index}">
                    <button class="accordion-button collapsed" type="button"
                            data-bs-toggle="collapse"
                            data-bs-target="#collapse${status.index}"
                            aria-expanded="false"
                            aria-controls="collapse${status.index}">
                            ${product.name}
                    </button>
                </h2>
                <div id="collapse${status.index}" class="accordion-collapse collapse"
                     aria-labelledby="heading${status.index}" data-bs-parent="#productAccordion">
                    <div class="accordion-body">
                        <ul class="list-group subsystem-list">
                            <c:if test="${empty product.subsystems}">
                                <div class="text-muted">Нет подсистем</div>
                            </c:if>
                            <c:forEach var="subsystem" items="${product.subsystems}">
                                <li class="list-group-item">
                                    <strong>${subsystem.name}</strong>
                                    <c:if test="${not empty subsystem.tests}">
                                        <ul class="mt-2">
                                            <c:forEach var="test" items="${subsystem.tests}">
                                                <li style="margin-left: 20px;">
                                                    🔹 ${test.name}
                                                    <small class="text-muted">[Группа: ${test.group},
                                                        Приоритет: ${test.priority}]</small>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </c:if>

                                </li>
                            </c:forEach>

                        </ul>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
