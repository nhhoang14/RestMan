package com.RestMan.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    protected Connection con;

    public DAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/restaurant", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
