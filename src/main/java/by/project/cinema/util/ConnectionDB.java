package by.project.cinema.util;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public final class ConnectionDB {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    public static Connection open() {
        try {
            return DriverManager.getConnection(
                    Properties.get(URL_KEY),
                    Properties.get(USERNAME_KEY),
                    Properties.get(PASSWORD_KEY));
        } catch (SQLException e) {
            log.error("Error connecting to database");
            throw new RuntimeException("Error connecting to database" + e.getMessage());
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Driver mysql can't be find");
            e.printStackTrace();
        }
    }

    private ConnectionDB() {
    }
}
