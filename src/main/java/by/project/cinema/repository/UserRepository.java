package by.project.cinema.repository;

import by.project.cinema.model.User;

import java.util.List;

public interface UserRepository {
    boolean create(User user);

    boolean update(User user);

    boolean delete(int id);

    List<User> getUsers();

    User getById(int id);


}
