<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết đơn đặt món</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager.css?v=20261120">
</head>
<body>
    <main class="page">
        <section class="content">
            <div class="page-header-row">
                <a class="back-link"
                   href="${pageContext.request.contextPath}/customer-revenue?action=history&amp;customerId=${customerId}&amp;customerName=${customerName}&amp;startDate=${startDate}&amp;endDate=${endDate}">
                    Quay lại
                </a>
                <div class="table-title">Đơn đặt món #${order.id}</div>
            </div>
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
                            <td colspan="4">Tổng tạm tính</td>
                            <td class="number"><fmt:formatNumber value="${order.estimatedTotalAmount}" pattern="#,##0"/> VND</td>
                        </tr>
                        <tr>
                            <td colspan="4">Tiền giảm giá</td>
                            <td class="number">
                                <fmt:formatNumber value="${empty bill ? 0 : bill.discountAmount}" pattern="#,##0"/> VND
                            </td>
                        </tr>
                        <tr class="total-row">
                            <td colspan="4">Tiền cuối cùng</td>
                            <td class="number">
                                <fmt:formatNumber value="${empty bill ? order.estimatedTotalAmount : bill.totalAmount}" pattern="#,##0"/> VND
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <a class="btn-primary action-bottom"
               href="${pageContext.request.contextPath}/customer-revenue?action=bill&amp;orderId=${order.id}&amp;customerId=${customerId}&amp;customerName=${customerName}&amp;startDate=${startDate}&amp;endDate=${endDate}">
                Xem hóa đơn
            </a>
        </section>
    </main>
</body>
</html>
