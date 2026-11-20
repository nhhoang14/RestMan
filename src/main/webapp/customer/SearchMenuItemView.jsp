<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tìm kiếm món ăn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css?v=20261120">
</head>
<body>
    <main class="page">
        <section class="content">
            <div class="page-header-row">
                <a class="back-link"
                   href="${pageContext.request.contextPath}/customer/CustomerMainView.jsp">
                    Quay lại
                </a>
                <h1 class="page-title">Tìm kiếm món ăn</h1>
            </div>

            <form class="form-row" method="get"
                  action="${pageContext.request.contextPath}/menuitem">
                <input type="search" name="keyword" value="${keyword}"
                       placeholder="Tìm món ăn" aria-label="Tìm món ăn">
                <button class="btn-primary" type="submit">Tìm kiếm</button>
            </form>

            <div class="table-wrap">
                <table class="data-table customer-table">
                    <thead>
                        <tr>
                            <th>Mã</th>
                            <th>Tên món</th>
                            <th>Giá</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${menuItems}" varStatus="status">
                            <tr>
                                <td>${item.id}</td>
                                <td>${empty item.name ? '-' : item.name}</td>
                                <td class="number">
                                    <fmt:formatNumber value="${item.price}" pattern="#,##0"/> VND
                                </td>
                                <td class="center">
                                    <a class="detail-button"
                                       href="${pageContext.request.contextPath}/menuitem?action=detail&amp;id=${item.id}">
                                        Xem chi tiết
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty menuItems}">
                            <tr>
                                <td class="center" colspan="4">Không tìm thấy món ăn.</td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </section>
    </main>
</body>
</html>
