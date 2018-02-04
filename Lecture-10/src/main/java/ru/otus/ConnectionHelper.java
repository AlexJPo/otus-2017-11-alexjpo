package ru.otus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            String url = "jdbc:postgresql://127.0.0.1:5432/otus?currentSchema=public&user=postgres";

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
