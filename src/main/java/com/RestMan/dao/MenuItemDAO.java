package com.RestMan.dao;

import java.sql.*;
import java.util.ArrayList;
import com.RestMan.model.*;

public class MenuItemDAO extends DAO {
    public MenuItemDAO() {
        super();
    }

    // Tìm danh sách món ăn có tên chứa từ khóa
    public ArrayList<MenuItem6> searchMenuItemByName(String name) {
        String sql = """
            SELECT id, name, price
            FROM tblMenuItem6
            WHERE LOWER(name) LIKE LOWER(?)
            ORDER BY name
            """;
        ArrayList<MenuItem6> result = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + (name == null ? "" : name.trim()) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MenuItem6 item = new MenuItem6();
                    item.setId(rs.getInt("id"));
                    item.setName(rs.getString("name"));
                    item.setPrice(rs.getFloat("price"));
                    result.add(item);
                }
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể tìm món ăn", e);
        }
    }

    // Lấy thông tin chi tiết một món ăn
    public MenuItem6 getMenuItemDetail(int id) {
        String sql = """
            SELECT m.id, m.name, m.image, m.price, m.description,
                c.id category_id, c.name category_name, d.size, d.unit,
                co.tblMenuItemid combo_id, co.startDate, co.endDate
            FROM tblMenuItem6 m
            LEFT JOIN tblCategory6 c ON c.id = m.tblCategoryid
            LEFT JOIN tblDish6 d ON d.tblMenuItemid = m.id
            LEFT JOIN tblCombo6 co ON co.tblMenuItemid = m.id
            WHERE m.id = ?
            """;
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                MenuItem6 item;
                if (rs.getObject("combo_id") != null) {
                    item = new Combo6();
                } else if (rs.getString("size") != null || rs.getString("unit") != null) {
                    item = new Dish6();
                } else {
                    item = new MenuItem6();
                }
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setImage(rs.getString("image"));
                item.setPrice(rs.getFloat("price"));
                item.setDescription(rs.getString("description"));

                // Lấy thông tin loại
                if (rs.getObject("category_id") != null) {
                    Category6 category = new Category6();
                    category.setId(rs.getInt("category_id"));
                    category.setName(rs.getString("category_name"));
                    item.setCategory(category);
                }

                // Lấy thông tin chi tiết theo loại món ăn
                if (item instanceof Dish6 dish) {
                    dish.setSize(rs.getString("size"));
                    dish.setUnit(rs.getString("unit"));
                } else if (item instanceof Combo6 combo) {
                    combo.setStartDate(rs.getTimestamp("startDate"));
                    combo.setEndDate(rs.getTimestamp("endDate"));
                }

                // Nếu là combo, lấy danh sách chi tiết món ăn trong combo
                if (item instanceof Combo6 combo) {
                    String detailSql = """
                        SELECT cd.id, cd.dishQuantity, d.tblMenuItemid,
                            m.name, m.image, m.price, m.description, d.size, d.unit
                        FROM tblComboDetail6 cd
                        JOIN tblDish6 d ON d.tblMenuItemid = cd.tblDishid
                        JOIN tblMenuItem6 m ON m.id = d.tblMenuItemid
                        WHERE cd.tblComboid = ?
                        """;
                    ArrayList<ComboDetail6> details = new ArrayList<>();
                    try (PreparedStatement detailPs = con.prepareStatement(detailSql)) {
                        detailPs.setInt(1, id);
                        try (ResultSet detailRs = detailPs.executeQuery()) {
                            while (detailRs.next()) {
                                Dish6 dish = new Dish6();
                                dish.setId(detailRs.getInt("tblMenuItemid"));
                                dish.setName(detailRs.getString("name"));
                                dish.setImage(detailRs.getString("image"));
                                dish.setPrice(detailRs.getFloat("price"));
                                dish.setDescription(detailRs.getString("description"));
                                dish.setSize(detailRs.getString("size"));
                                dish.setUnit(detailRs.getString("unit"));

                                ComboDetail6 detail = new ComboDetail6();
                                detail.setId(detailRs.getInt("id"));
                                detail.setDishQuantity(detailRs.getInt("dishQuantity"));
                                detail.setDish(dish);
                                details.add(detail);
                            }
                        }
                    }
                    combo.setComboDetails(details);
                }
                return item;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể lấy chi tiết món ăn", e);
        }
    }

}
