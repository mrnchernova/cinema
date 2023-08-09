package by.project.cinema.repository;

import by.project.cinema.model.User;
import by.project.cinema.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean create(User user) {
        try (Connection connection = ConnectionDB.open()) {

            PreparedStatement statement = connection.prepareStatement("INSERT INTO person (username, password, email) VALUES (?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.execute();
            System.out.println("database successfully updated");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
