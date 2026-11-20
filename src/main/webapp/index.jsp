<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
    <c:when test="${not empty sessionScope.manager}">
        <c:redirect url="/manager/ManagerMainView.jsp"/>
    </c:when>
    <c:otherwise>
        <c:redirect url="/customer/CustomerMainView.jsp"/>
    </c:otherwise>
</c:choose>
