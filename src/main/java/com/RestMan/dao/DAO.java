package com.RestMan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO implements AutoCloseable {
    protected Connection con;

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/restmandb?serverTimezone=Asia/Ho_Chi_Minh";
            String user = "root";
            String password = "123456";

            con = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Không tìm thấy MySQL JDBC Driver", e);
        } catch (SQLException e) {
            throw new IllegalStateException( "Không thể kết nối cơ sở dữ liệu RestMan", e);
        }
    }

    @Override
    public void close() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Không thể đóng kết nối cơ sở dữ liệu", e);
        }
    }
}