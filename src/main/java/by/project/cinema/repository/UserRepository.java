package by.project.cinema.repository;

import by.project.cinema.model.User;

import java.util.List;

public interface UserRepository {
    boolean create(User user);

    boolean updateUser(User user);

    boolean delete(int id);

    List<User> getUsers();

    User getById(int id);

    boolean isExistUser(int id);

    boolean isExistUserByUsername(String username);

    User signIn(String username, String password);


}
