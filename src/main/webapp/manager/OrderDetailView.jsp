<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết đơn đặt món</title>
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
            <a class="back-link" href="${pageContext.request.contextPath}/manager/CustomerOrderHistoryView.jsp">Quay lại</a>
            <div class="table-title">Đơn đặt món #${order.id}</div>
            <div class="table-wrap">
                <table class="data-table manager-table">
                    <thead>
                        <tr><th>TT</th><th>Mã món</th><th>Số lượng</th><th>Đơn giá</th><th>Thành tiền</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${order.details}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>${detail.menuItem.id}</td>
                                <td class="center">${detail.quantity}</td>
                                <td class="number"><fmt:formatNumber value="${detail.price}" pattern="#,##0"/> VND</td>
                                <td class="number"><fmt:formatNumber value="${detail.amount}" pattern="#,##0"/> VND</td>
                            </tr>
                        </c:forEach>
                        <tr class="total-row">
                            <td colspan="4">Tổng cộng</td>
                            <td class="number"><fmt:formatNumber value="${order.totalAmount}" pattern="#,##0"/> VND</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <a class="btn-primary action-bottom"
               href="${pageContext.request.contextPath}/customer-revenue?action=bill&amp;orderId=${order.id}">
                Xem hóa đơn
            </a>
        </section>
    </main>
</body>
</html>
