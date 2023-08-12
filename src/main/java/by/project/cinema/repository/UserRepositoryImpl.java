package by.project.cinema.repository;

import by.project.cinema.model.Role;
import by.project.cinema.model.User;
import by.project.cinema.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public boolean create(User user) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO person (username, password, email, role) VALUES (?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, Role.USER.toString());
            statement.execute();
            System.out.println("database successfully updated");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        return false;
        // "UPDATE users SET password=? WHERE id = ?"
    }


    /* TODO удалить данные из БД с проверкой*/
    @Override
    public boolean delete(int id) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("user successfully deleted");//!!!!!!!!
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("user not deleted");
        return false;
    }

    @Override
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                User u = new User(resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("email"),
                        Role.valueOf(resultSet.getString("role")));

                userList.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getById(int userId) {
        User user = null;
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                Role role = Role.valueOf(resultSet.getString("role"));
                user = new User(id, username, password, email, role);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
}
