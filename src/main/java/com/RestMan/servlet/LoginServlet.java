package com.RestMan.servlet;

import com.RestMan.dao.ManagerLoginDAO;
import com.RestMan.model.Manager6;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("logout".equals(request.getParameter("action"))) {
            request.getSession().invalidate();
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.getRequestDispatcher("/LoginView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            request.setAttribute("error", "Vui lòng nhập tên đăng nhập và mật khẩu.");
            doGet(request, response);
            return;
        }

        try (ManagerLoginDAO dao = new ManagerLoginDAO()) {
            Manager6 manager = dao.authenticate(username.trim(), password);
            if (manager == null) {
                request.setAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng.");
                doGet(request, response);
                return;
            }
            request.getSession(true).setAttribute("manager", manager);
            response.sendRedirect(request.getContextPath() + "/manager/ManagerMainView.jsp");
        } catch (IllegalStateException e) {
            throw new ServletException("Không thể đăng nhập quản lý", e);
        }
    }
}
