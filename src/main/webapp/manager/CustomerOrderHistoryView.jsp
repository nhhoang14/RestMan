<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử gọi món của khách hàng</title>
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
            <c:choose>
                <c:when test="${not empty startDate and not empty endDate}">
                    <a class="back-link"
                       href="${pageContext.request.contextPath}/customer-revenue?action=report&amp;startDate=${startDate}&amp;endDate=${endDate}">
                        Quay lại
                    </a>
                </c:when>
                <c:otherwise>
                    <a class="back-link"
                       href="${pageContext.request.contextPath}/customer-revenue?action=report">
                        Quay lại
                    </a>
                </c:otherwise>
            </c:choose>
            <div class="table-title">${empty customer.fullName ? 'Khách hàng' : customer.fullName}</div>
            <div class="table-wrap">
                <table class="data-table manager-table">
                    <thead>
                        <tr><th>TT</th><th>Mã đặt món</th><th>Thời gian</th><th>Tổng tiền</th><th>Trạng thái</th></tr>
                    </thead>
                    <tbody>
                        <c:set var="historyTotal" value="${0}"/>
                        <c:forEach var="order" items="${orders}" varStatus="status">
                            <c:set var="historyTotal" value="${historyTotal + order.estimatedTotalAmount}"/>
                            <tr>
                                <td>${status.count}</td>
                                <td>
                                    <a class="table-link"
                                       href="${pageContext.request.contextPath}/customer-revenue?action=order&amp;orderId=${order.id}&amp;customerId=${customerId}&amp;customerName=${customerName}&amp;startDate=${startDate}&amp;endDate=${endDate}">
                                        ${order.id}
                                    </a>
                                </td>
                                <td><fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm"/></td>
                                <td class="number"><fmt:formatNumber value="${order.estimatedTotalAmount}" pattern="#,##0"/> VND</td>
                                <td class="center ${order.status == 'Thành công' ? 'status-success' : ''}">${empty order.status ? '-' : order.status}</td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty orders}">
                            <tr><td class="center" colspan="5">Chưa có lịch sử gọi món.</td></tr>
                        </c:if>
                        <tr class="total-row">
                            <td colspan="4">Tổng tiền tất cả đơn</td>
                            <td class="number">
                                <fmt:formatNumber value="${historyTotal}" pattern="#,##0"/> VND
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</body>
</html>
