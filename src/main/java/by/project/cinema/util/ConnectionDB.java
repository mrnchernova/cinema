package by.project.cinema.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionDB {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    public static Connection open() {
        try {
            System.out.println("Connection open() is working");
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к БД" + e.getMessage());
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("loadDriver() is working");
        } catch (
                ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionDB() {}
}
