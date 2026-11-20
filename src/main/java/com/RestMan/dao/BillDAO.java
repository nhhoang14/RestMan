package com.RestMan.dao;

import java.sql.*;
import com.RestMan.model.*;

public class BillDAO extends DAO {
    public BillDAO() {
        super();
    }

    public Bill6 getBillByOrder(DishOrdering6 d) {
        if (d == null) throw new IllegalArgumentException("Đơn gọi món không được null");
        String sql = """
            SELECT b.id, b.paymentTime, b.paymentMethod,
                b.discountAmount, b.totalAmount,
                s.tblStaffid, m.name staff_name
            FROM tblBill6 b
            LEFT JOIN tblSalesStaff6 s ON s.tblStaffid = b.tblSalesStaffid
            LEFT JOIN tblMember6 m ON m.id = s.tblStaffid
            WHERE b.tblDishOrderingid = ?
            """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                Bill6 bill = new Bill6();
                bill.setId(rs.getInt("id"));
                bill.setPaymentTime(rs.getTimestamp("paymentTime"));
                bill.setPaymentMethod(rs.getString("paymentMethod"));
                bill.setDiscountAmount(rs.getFloat("discountAmount"));
                bill.setTotalAmount(rs.getFloat("totalAmount"));
                bill.setDishOrdering(d);
                if (rs.getObject("tblStaffid") != null) {
                    SalesStaff6 staff = new SalesStaff6();
                    staff.setId(rs.getInt("tblStaffid"));
                    staff.setFullName(rs.getString("staff_name"));
                    bill.setSalesStaff(staff);
                }
                return bill;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể lấy hóa đơn", e);
        }
    }
}
