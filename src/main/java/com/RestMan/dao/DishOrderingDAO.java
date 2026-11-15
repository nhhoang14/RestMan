package com.RestMan.dao;

import com.RestMan.model.Customer6;
import com.RestMan.model.DishOrdering6;
import com.RestMan.model.DishOrderingDetail6;
import com.RestMan.model.MenuItem6;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishOrderingDAO extends DAO {
    public DishOrderingDAO() {
        super();
    }

    // Lịch sử gọi món của khách hàng
    public ArrayList<DishOrdering6> getDishOrderingByCustomer(Customer6 customer) {
        String sql = """
                SELECT o.id, o.orderDate, o.estimatedTotalAmount, o.status
                FROM tblDishOrdering6 o
                JOIN tblTableBooking6 tb ON tb.id = o.tblTableBookingid
                WHERE tb.tblCustomerid %s
                ORDER BY o.orderDate DESC
                """.formatted(customer == null ? "IS NULL" : "= ?");

        ArrayList<DishOrdering6> orders = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            if (customer != null) ps.setInt(1, customer.getId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DishOrdering6 order = new DishOrdering6();
                    order.setId(rs.getInt("id"));
                    order.setOrderDate(rs.getTimestamp("orderDate"));
                    order.setEstimatedTotalAmount(rs.getFloat("estimatedTotalAmount"));
                    order.setStatus(rs.getString("status"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể lấy lịch sử gọi món", e);
        }
        return orders;
    }

    // Chi tiết đơn đặt món
    public DishOrdering6 getOrderDetail(DishOrdering6 input) {
        if (input == null) {
            throw new IllegalArgumentException("Đơn gọi món không được null");
        }

        String sql = """
            SELECT o.id, o.orderDate, o.estimatedTotalAmount, o.status,
                od.id detail_id, od.quantity, od.price detail_price,
                m.id item_id, m.name, m.image, m.description, m.price item_price
            FROM tblDishOrdering6 o
            LEFT JOIN tblDishOrderingDetail6 od
                ON od.tblDishOrderingid = o.id
            LEFT JOIN tblMenuItem6 m
                ON m.id = od.tblMenuItemid
            WHERE o.id = ?
            ORDER BY od.id
            """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, input.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }

                DishOrdering6 order = new DishOrdering6();
                order.setId(rs.getInt("id"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setEstimatedTotalAmount(rs.getFloat("estimatedTotalAmount"));
                order.setStatus(rs.getString("status"));
                ArrayList<DishOrderingDetail6> details = new ArrayList<>();
                float detailTotal = 0;
                do {
                    if (rs.getObject("detail_id") != null) {
                        MenuItem6 menuItem = new MenuItem6();
                        menuItem.setId(rs.getInt("item_id"));
                        menuItem.setName(rs.getString("name"));
                        menuItem.setImage(rs.getString("image"));
                        menuItem.setDescription(rs.getString("description"));
                        menuItem.setPrice(rs.getFloat("item_price"));

                        DishOrderingDetail6 detail = new DishOrderingDetail6();
                        detail.setId(rs.getInt("detail_id"));
                        detail.setQuantity(rs.getInt("quantity"));
                        detail.setPrice(rs.getFloat("detail_price"));
                        detail.setMenuItem(menuItem);
                        details.add(detail);
                        detailTotal += detail.getQuantity() * detail.getPrice();
                    }
                } while (rs.next());
                order.setDetails(details);
                order.setEstimatedTotalAmount(detailTotal);
                return order;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể lấy chi tiết đơn gọi món", e);
        }
    }
}
