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
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/customer/CustomerMainView.jsp">RestMan</a>
            <div class="topbar-actions">
                <span class="greeting">
                    Xin chào, ${empty sessionScope.customer.fullName ? 'khách hàng' : sessionScope.customer.fullName}
                </span>
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
        </header>

        <section class="content">
            <h1 class="page-title">Trang chính khách hàng</h1>
            <div class="customer-actions">
                <a class="btn-primary"
                   href="${pageContext.request.contextPath}/menuitem?keyword=">
                    Tìm kiếm món ăn
                </a>
            </div>
        </section>
    </main>
</body>
</html>
