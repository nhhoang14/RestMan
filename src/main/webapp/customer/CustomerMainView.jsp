<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giao diện chính của khách hàng</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css">
</head>
<body>
    <main class="page">
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/customer/CustomerMainView.jsp">RestMan</a>
            <span class="greeting">Xin chào, khách hàng</span>
        </header>

        <section class="content">
            <h1 class="page-title">Trang chính khách hàng</h1>
            <div class="customer-actions">
                <a class="btn-primary"
                   href="${pageContext.request.contextPath}/menuitem?keyword=">
                    Tìm kiếm món ăn
                </a>
                <a class="secondary-link"
                   href="${pageContext.request.contextPath}/login">
                    Đăng nhập quản lý
                </a>
            </div>
        </section>
    </main>
</body>
</html>
