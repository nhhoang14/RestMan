package com.RestMan.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.RestMan.dao.MenuItemDAO;

// Xử lý module tìm kiếm thông tin món ăn
@WebServlet("/menuitem")
public class MenuItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = request.getParameter("name");
        }
        if (action == null) {
            action = "search";
        }
        try {
            if ("search".equals(action)) {
                try (MenuItemDAO dao = new MenuItemDAO()) {
                    request.setAttribute("menuItems", dao.searchMenuItemByName(keyword));
                }
                request.setAttribute("keyword", keyword == null ? "" : keyword);
                forward(request, response, "/customer/SearchMenuItemView.jsp");
            } else if ("detail".equals(action)) {
                int id = parsePositiveInt(request.getParameter("id"), "Mã món ăn");
                try (MenuItemDAO dao = new MenuItemDAO()) {
                    request.setAttribute("menuItem", dao.getMenuItemDetail(id));
                }
                if (request.getAttribute("menuItem") == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy món ăn");
                    return;
                }
                forward(request, response, "/customer/MenuItemDetailView.jsp");
            } else {
                forward(request, response, "/customer/CustomerMainView.jsp");
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ServletException("Lỗi truy cập dữ liệu món ăn", e);
        }
    }

    private static int parsePositiveInt(String value, String field) {
        try {
            int id = Integer.parseInt(value);
            if (id <= 0) throw new NumberFormatException();
            return id;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(field + " không hợp lệ");
        }
    }

    private static void forward(HttpServletRequest request, HttpServletResponse response, String path)
            throws ServletException, IOException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
