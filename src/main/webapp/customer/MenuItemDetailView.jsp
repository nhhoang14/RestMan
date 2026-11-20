<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<fmt:setLocale value="vi_VN"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chi tiết món ăn</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css?v=20261120">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css?v=20261120">
</head>
<body>
    <main class="page">
        <section class="content">
            <div class="page-header-row">
                <div class="table-title">${empty menuItem.name ? '-' : menuItem.name}</div>
            </div>
            <c:choose>
                <c:when test="${not empty menuItem.image}">
                    <c:choose>
                        <c:when test="${fn:startsWith(menuItem.image, 'http://') or fn:startsWith(menuItem.image, 'https://')}">
                            <img class="menu-image" src="${menuItem.image}" alt="${menuItem.name}">
                        </c:when>
                        <c:otherwise>
                            <img class="menu-image"
                                 src="${pageContext.request.contextPath}/${menuItem.image}"
                                 alt="${menuItem.name}">
                        </c:otherwise>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <div class="menu-image-placeholder">Chưa có hình ảnh</div>
                </c:otherwise>
            </c:choose>
            <div class="table-wrap">
                <table class="data-table detail-table">
                    <tbody>
                        <tr><th>Mã</th><td>${menuItem.id}</td></tr>
                        <tr><th>Mô tả</th><td>${empty menuItem.description ? '-' : menuItem.description}</td></tr>
                        <tr><th>Loại</th><td>${empty menuItem.category.name ? '-' : menuItem.category.name}</td></tr>
                        <c:if test="${not empty dishSize or not empty dishUnit}">
                            <tr><th>Size</th><td>${empty dishSize ? '-' : dishSize}</td></tr>
                            <tr><th>Đơn vị tính</th><td>${empty dishUnit ? '-' : dishUnit}</td></tr>
                        </c:if>
                        <c:if test="${not empty comboStartDate or not empty comboEndDate}">
                            <tr>
                                <th>Ngày bắt đầu</th>
                                <td>
                                    <fmt:formatDate value="${comboStartDate}" pattern="dd/MM/yyyy"
                                                    timeZone="Asia/Ho_Chi_Minh"/>
                                </td>
                            </tr>
                            <tr>
                                <th>Ngày kết thúc</th>
                                <td>
                                    <fmt:formatDate value="${comboEndDate}" pattern="dd/MM/yyyy"
                                                    timeZone="Asia/Ho_Chi_Minh"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>Giá</th>
                            <td><fmt:formatNumber value="${menuItem.price}" pattern="#,##0"/> VND</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <c:if test="${not empty comboDetails}">
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
                            <c:forEach var="detail" items="${comboDetails}">
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
