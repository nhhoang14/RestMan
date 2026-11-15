package com.RestMan.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import com.RestMan.dao.BillDAO;
import com.RestMan.dao.CustomerStatsDAO;
import com.RestMan.dao.DishOrderingDAO;
import com.RestMan.model.Customer6;
import com.RestMan.model.DishOrdering6;

// Xử lý module thống kê khách hàng theo doanh thu
@WebServlet("/customer-revenue")
public class CustomerRevenueServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null && request.getParameter("startDate") != null
                && request.getParameter("endDate") != null) {
            action = "report";
        }
        try {
            if ("report".equals(action)) {
                Date start = parseDate(request.getParameter("startDate"), "Ngày bắt đầu");
                Date end = parseDate(request.getParameter("endDate"), "Ngày kết thúc");
                if (start == null) {
                    start = Date.valueOf(LocalDate.now().minusMonths(6));
                }
                if (end == null) {
                    end = Date.valueOf(LocalDate.now());
                }
                try (CustomerStatsDAO dao = new CustomerStatsDAO()) {
                    List<com.RestMan.model.CustomerStats6> stats =
                            dao.getCustomerRevenueReport(start, end);
                    request.setAttribute("customers", stats);
                    request.setAttribute("customerStats", stats);
                    request.setAttribute("totalCount",
                            stats.stream().mapToInt(com.RestMan.model.CustomerStats6::getBookingCount).sum());
                    request.setAttribute("totalRevenue",
                            stats.stream().mapToDouble(com.RestMan.model.CustomerStats6::getTotalRevenue).sum());
                }
                request.setAttribute("startDate", start);
                request.setAttribute("endDate", end);
                forward(request, response, "/manager/CustomerRevenueReportView.jsp");
            } else if ("history".equals(action)) {
                int customerId = parsePositiveInt(request.getParameter("customerId"), "Mã khách hàng");
                Customer6 customer = new Customer6();
                customer.setId(customerId);
                request.setAttribute("customer", customer);
                try (DishOrderingDAO dao = new DishOrderingDAO()) {
                    request.setAttribute("orders", dao.getDishOrderingByCustomer(customer));
                }
                request.setAttribute("customerId", customerId);
                forward(request, response, "/manager/CustomerOrderHistoryView.jsp");
            } else if ("order".equals(action)) {
                int orderId = parsePositiveInt(request.getParameter("orderId"), "Mã đơn hàng");
                DishOrdering6 order = new DishOrdering6();
                order.setId(orderId);
                try (DishOrderingDAO dao = new DishOrderingDAO()) {
                    order = dao.getOrderDetail(order);
                }
                if (order == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng");
                    return;
                }
                request.setAttribute("order", order);
                forward(request, response, "/manager/OrderDetailView.jsp");
            } else if ("bill".equals(action)) {
                int orderId = parsePositiveInt(request.getParameter("orderId"), "Mã đơn hàng");
                DishOrdering6 order = new DishOrdering6();
                order.setId(orderId);
                try (DishOrderingDAO orderDao = new DishOrderingDAO();
                        BillDAO billDao = new BillDAO()) {
                    order = orderDao.getOrderDetail(order);
                    if (order == null) {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy đơn hàng");
                        return;
                    }
                    request.setAttribute("bill", billDao.getBillByOrder(order));
                }
                forward(request, response, "/manager/BillView.jsp");
            } else if ("select".equals(action)) {
                forward(request, response, "/manager/SelectReportView.jsp");
            } else {
                forward(request, response, "/manager/ManagerMainView.jsp");
            }
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (IllegalStateException e) {
            throw new ServletException("Lỗi truy cập dữ liệu báo cáo", e);
        }
    }

    private static Date parseDate(String value, String field) {
        try {
            if (value == null || value.isBlank()) {
                return null;
            }
            return Date.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(field + " không hợp lệ, định dạng yyyy-MM-dd");
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
