package com.RestMan.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import com.RestMan.model.*;

public class CustomerStatsDAO extends DAO {
    public CustomerStatsDAO() {
        super();
    }

    // Thống kê khách hàng theo doanh thu trong khoảng thời gian
    public ArrayList<CustomerStats6> getCustomerRevenueReport(Date startDate, Date endDate) {
        Date today = java.sql.Date.valueOf(LocalDate.now());
        Date effectiveStartDate = startDate == null
                ? java.sql.Date.valueOf(LocalDate.now().minusMonths(6)) : startDate;
        Date effectiveEndDate = endDate == null ? today : endDate;
        if (effectiveStartDate.after(effectiveEndDate)) {
            throw new IllegalArgumentException("Khoảng thời gian thống kê không hợp lệ");
        }
        String sql = """
            SELECT COALESCE(c.tblMemberid, 0) customer_id,
                COALESCE(m.name, 'Khách vãng lai') customer_name,
                m.phone,
                COUNT(DISTINCT CASE WHEN b.id IS NOT NULL THEN tb.id END) booking_count,
                COALESCE(SUM(b.totalAmount), 0) total_revenue
            FROM tblTableBooking6 tb
            LEFT JOIN tblCustomer6 c ON c.tblMemberid = tb.tblCustomerid
            LEFT JOIN tblMember6 m ON m.id = c.tblMemberid
            LEFT JOIN tblDishOrdering6 o ON o.tblTableBookingid = tb.id
            LEFT JOIN tblBill6 b ON b.tblDishOrderingid = o.id
                AND b.paymentTime >= ?
                AND b.paymentTime < DATE_ADD(?, INTERVAL 1 DAY)
            GROUP BY c.tblMemberid, m.name, m.phone
            HAVING total_revenue > 0
            ORDER BY total_revenue DESC
            """;
        ArrayList<CustomerStats6> result = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(effectiveStartDate.getTime()));
            ps.setDate(2, new java.sql.Date(effectiveEndDate.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerStats6 customer = new CustomerStats6();
                    customer.setId(rs.getInt("customer_id"));
                    customer.setFullName(rs.getString("customer_name"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setBookingCount(rs.getInt("booking_count"));
                    customer.setTotalRevenue(rs.getFloat("total_revenue"));
                    result.add(customer);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể lập báo cáo doanh thu khách hàng", e);
        }
    }
}
