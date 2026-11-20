<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Giao diện chính của quản lý</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager.css?v=20261120">
</head>
<body>
    <main class="page">
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/manager/ManagerMainView.jsp">RestMan</a>
            <div class="topbar-actions">
                <span class="greeting">
                    Xin chào, ${empty sessionScope.manager.fullName ? 'quản lý' : sessionScope.manager.fullName}
                </span>
                <a class="secondary-link"
                   href="${pageContext.request.contextPath}/login?action=logout">
                    Đăng xuất
                </a>
            </div>
        </header>
        <section class="content">
            <h1 class="page-title">Trang chính quản lý</h1>
            <div class="manager-actions">
                <a class="btn-primary"
                   href="${pageContext.request.contextPath}/manager/SelectReportView.jsp">
                    Xem báo cáo
                </a>
            </div>
        </section>
    </main>
</body>
</html>
