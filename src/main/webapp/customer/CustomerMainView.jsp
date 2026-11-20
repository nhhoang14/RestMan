<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giao diện chính của khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css?v=20261120">
</head>
<body>
    <main class="page">
        <section class="content">
            <div class="page-header-row">
                <h1 class="page-title">RestMan</h1>
            </div>
            <div class="customer-actions">
                <a class="btn-primary"
                   href="${pageContext.request.contextPath}/menuitem?keyword=">
                    Tìm kiếm món ăn
                </a>
                <c:choose>
                    <c:when test="${not empty sessionScope.customer}">
                        <a class="secondary-link"
                           href="${pageContext.request.contextPath}/login?action=logout">
                            Đăng xuất
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a class="secondary-link"
                           href="${pageContext.request.contextPath}/login">
                            Đăng nhập
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </main>
</body>
</html>
