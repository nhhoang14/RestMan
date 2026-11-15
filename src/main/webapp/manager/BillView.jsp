<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hóa đơn</title>
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
            <a class="back-link" href="${pageContext.request.contextPath}/manager/OrderDetailView.jsp">Quay lại</a>
            <div class="table-title">Hóa đơn #${bill.id}</div>
            <div class="table-wrap">
                <table class="data-table detail-table">
                    <tbody>
                        <tr><th>Mã hóa đơn</th><td>${bill.id}</td></tr>
                        <tr><th>Thời gian thanh toán</th><td><fmt:formatDate value="${bill.paymentTime}" pattern="dd/MM/yyyy HH:mm"/></td></tr>
                        <tr><th>Hình thức thanh toán</th><td>${empty bill.paymentMethod ? '-' : bill.paymentMethod}</td></tr>
                        <tr><th>Nhân viên bán hàng</th><td>${empty bill.salesStaff.fullName ? '-' : bill.salesStaff.fullName}</td></tr>
                        <tr><th>Số tiền giảm giá</th><td><fmt:formatNumber value="${bill.discountAmount}" pattern="#,##0"/> VND</td></tr>
                        <tr class="total-row"><th>Tổng tiền</th><td><fmt:formatNumber value="${bill.totalAmount}" pattern="#,##0"/> VND</td></tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</body>
</html>
