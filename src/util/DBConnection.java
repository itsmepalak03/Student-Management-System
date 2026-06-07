package com.royal.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URLNAME = "jdbc:mysql://localhost:3306/genlj24";
    private static final String DRIVERCLASS = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private DBConnection() {}

    public static Connection getDBInstance() {

        Connection conn = null;

        try {
            Class.forName(DRIVERCLASS);
            conn = DriverManager.getConnection(URLNAME, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
