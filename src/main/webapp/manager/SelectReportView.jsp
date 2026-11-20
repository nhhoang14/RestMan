<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chọn loại thống kê</title>
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
            <a class="back-link" href="${pageContext.request.contextPath}/manager/ManagerMainView.jsp">Quay lại</a>
            <h1 class="page-title">Chọn loại thống kê</h1>
            <div class="manager-actions">
                <a class="btn-primary"
                   href="${pageContext.request.contextPath}/manager/CustomerRevenueReportView.jsp">
                    Xem thống kê khách hàng theo doanh thu
                </a>
            </div>
        </section>
    </main>
</body>
</html>
