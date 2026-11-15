<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử gọi món của khách hàng</title>
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
            <a class="back-link" href="${pageContext.request.contextPath}/manager/CustomerRevenueReportView.jsp">Quay lại</a>
            <div class="table-title">${empty customer.fullName ? 'Khách hàng' : customer.fullName}</div>
            <div class="table-wrap">
                <table class="data-table manager-table">
                    <thead>
                        <tr><th>TT</th><th>Mã đặt món</th><th>Thời gian</th><th>Tổng tiền</th><th>Trạng thái</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>
                                    <a class="table-link"
                                       href="${pageContext.request.contextPath}/customer-revenue?action=order&amp;orderId=${order.id}">
                                        ${order.id}
                                    </a>
                                </td>
                                <td><fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                                <td class="number"><fmt:formatNumber value="${order.totalAmount}" pattern="#,##0"/> VND</td>
                                <td class="${order.status == 'Thành công' ? 'status-success' : ''}">${empty order.status ? '-' : order.status}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty orders}">
                            <tr><td class="center" colspan="5">Chưa có lịch sử gọi món.</td></tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
            <p class="hint">Nhấn vào một đơn đặt món để xem chi tiết.</p>
        </section>
    </main>
</body>
</html>
