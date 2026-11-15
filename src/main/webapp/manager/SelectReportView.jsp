<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chọn loại thống kê</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager.css">
</head>
<body>
    <main class="page">
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/manager/ManagerMainView.jsp">RestMan</a>
            <span class="greeting">Xin chào, quản lý</span>
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
