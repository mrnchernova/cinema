package by.project.cinema.repository;

import by.project.cinema.model.User;

import java.util.List;

public interface UserRepository {
    boolean createUser(User user);

    boolean updateUser(User user);

    boolean delete(int id);

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String user);

    boolean isExistUser(int id);

    boolean isExistUserByUsername(String username);

    boolean signIn(String username, String password);


}
