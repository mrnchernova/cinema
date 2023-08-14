package by.project.cinema.service;

import by.project.cinema.model.User;

import java.util.List;

public interface UserService {
    boolean create(User user);

    boolean update(User user);

    boolean delete(int id);

    List<User> getUsers();

    User getById(int id);

    boolean isExistUser(int id);

    User signIn(String username, String password);
}
