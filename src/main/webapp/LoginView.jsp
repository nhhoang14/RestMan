<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập quản lý - RestMan</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/manager.css">
</head>
<body>
    <main class="page login-page">
        <header class="topbar">
            <a class="brand" href="${pageContext.request.contextPath}/">RestMan</a>
            <span class="greeting">Khu vực quản lý</span>
        </header>
        <section class="login-card">
            <h1 class="page-title">Đăng nhập quản lý</h1>
            <p class="hint">Vui lòng đăng nhập để xem báo cáo nhà hàng.</p>
            <c:if test="${not empty error}">
                <p class="form-error" role="alert">${error}</p>
            </c:if>
            <form class="login-form" method="post" action="${pageContext.request.contextPath}/login">
                <label for="username">Tên đăng nhập</label>
                <input id="username" name="username" type="text"
                       value="${param.username}" autocomplete="username" required>
                <label for="password">Mật khẩu</label>
                <input id="password" name="password" type="password"
                       autocomplete="current-password" required>
                <button class="btn-primary" type="submit">Đăng nhập</button>
            </form>
            <a class="back-link login-back" href="${pageContext.request.contextPath}/">
                Quay về trang khách hàng
            </a>
        </section>
    </main>
</body>
</html>
