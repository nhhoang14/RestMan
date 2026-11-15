<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết món ăn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css">
</head>
<body>
    <main class="page">
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/customer/CustomerMainView.jsp">RestMan</a>
            <span class="greeting">Xin chào, khách hàng</span>
        </header>

        <section class="content">
            <div class="table-title">${empty menuItem.name ? '-' : menuItem.name}</div>
            <c:if test="${not empty menuItem.image}">
                <img class="menu-image" src="${menuItem.image}" alt="${menuItem.name}">
            </c:if>
            <div class="table-wrap">
                <table class="data-table detail-table">
                    <tbody>
                        <tr><th>Mã</th><td>${menuItem.id}</td></tr>
                        <tr><th>Mô tả</th><td>${empty menuItem.description ? '-' : menuItem.description}</td></tr>
                        <tr><th>Size</th><td>${empty menuItem.size ? '-' : menuItem.size}</td></tr>
                        <tr><th>Đơn vị tính</th><td>${empty menuItem.unit ? '-' : menuItem.unit}</td></tr>
                        <tr><th>Loại</th><td>${empty menuItem.category.name ? '-' : menuItem.category.name}</td></tr>
                        <tr>
                            <th>Giá</th>
                            <td><fmt:formatNumber value="${menuItem.price}" pattern="#,##0"/> VND</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty menuItem.comboDetails}">
                <div class="table-title combo-title">Món trong combo</div>
                <div class="table-wrap">
                    <table class="data-table combo-table">
                        <thead>
                            <tr>
                                <th>Mã món</th>
                                <th>Tên món</th>
                                <th>Số lượng</th>
                                <th>Đơn giá</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="detail" items="${menuItem.comboDetails}">
                                <tr>
                                    <td class="center">${detail.dish.id}</td>
                                    <td>${empty detail.dish.name ? '-' : detail.dish.name}</td>
                                    <td class="center">${detail.dishQuantity}</td>
                                    <td class="number">
                                        <fmt:formatNumber value="${detail.dish.price}" pattern="#,##0"/> VND
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </c:if>
            <a class="btn-primary action-bottom"
               href="${pageContext.request.contextPath}/menuitem?keyword=">
                Quay lại
            </a>
        </section>
    </main>
</body>
</html>
