<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thống kê khách hàng theo doanh thu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager.css?v=20261120">
</head>
<body>
    <main class="page">
        <section class="content">
            <div class="page-header-row">
                <a class="back-link" href="${pageContext.request.contextPath}/manager/SelectReportView.jsp">Quay lại</a>
                <h1 class="page-title">Thống kê khách hàng theo doanh thu</h1>
            </div>
            <form class="filter-row" method="get"
                  action="${pageContext.request.contextPath}/customer-revenue">
                <div class="filter-field">
                    <label for="startDate">Từ</label>
                    <input id="startDate" type="date" name="startDate" value="${startDate}">
                </div>
                <div class="filter-field">
                    <label for="endDate">Đến</label>
                    <input id="endDate" type="date" name="endDate" value="${endDate}">
                </div>
                <button class="btn-primary" type="submit">Tìm kiếm</button>
            </form>
            <div class="table-wrap">
                <table class="data-table manager-table">
                    <thead>
                        <tr><th>TT</th><th>Tên khách hàng</th><th>SĐT</th><th>Số lượng gọi món</th><th>Doanh thu</th></tr>
                    </thead>
                    <tbody>
                        <c:forEach var="customer" items="${customerStats}" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${customer.id > 0 or customer.fullName == 'Khách vãng lai'}">
                                            <a class="table-link"
                                               href="${pageContext.request.contextPath}/customer-revenue?action=history&amp;customerId=${customer.id}&amp;customerName=${customer.fullName}&amp;startDate=${startDate}&amp;endDate=${endDate}">
                                                ${empty customer.fullName ? '-' : customer.fullName}
                                            </a>
                                        </c:when>
                                        <c:otherwise>${empty customer.fullName ? '-' : customer.fullName}</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${empty customer.phone ? 'NULL' : customer.phone}</td>
                                <td class="center">${customer.bookingCount}</td>
                                <td class="number"><fmt:formatNumber value="${customer.totalRevenue}" pattern="#,##0"/> VND</td>
                            </tr>
                        </c:forEach>
                        <tr class="total-row">
                            <td colspan="3">Tổng cộng</td>
                            <td class="center">${empty totalCount ? 0 : totalCount}</td>
                            <td class="number"><fmt:formatNumber value="${empty totalRevenue ? 0 : totalRevenue}" pattern="#,##0"/> VND</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</body>
</html>
