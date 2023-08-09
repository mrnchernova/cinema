package by.project.cinema.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionDB {
    private static final String URL = "jdbc:mysql://localhost:3307/cinema";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    static {
        loadDriver();
    }

    public static Connection open(){
        try {
            System.out.println("Connection open() is working");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
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

    private ConnectionDB() {

//
//        try (Connection connection = DriverManager.getConnection(url, username, password)) {
//            System.out.println("connection good...");
//
//            PreparedStatement statement = connection.prepareStatement("" +
//                    "INSERT INTO person (username, password, email) " +
//                    "VALUES ('Alex2', '1234', 'alex@test.ru')");
//            statement.execute();
//        } catch (
//                SQLException e) {
//            System.out.println("Ошибка подключения к базе данных");
//            e.printStackTrace();
//        }
    }

}
