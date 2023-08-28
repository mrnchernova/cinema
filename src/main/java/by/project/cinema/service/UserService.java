package by.project.cinema.service;

import by.project.cinema.model.User;


public interface UserService {
    void createUser();

    boolean updateUser(User user);

    boolean delete(int id);

    void getUsers();

    User getUserById(int id);


    boolean isExistUser(int id);

    boolean isExistUserByUsername(String username);

    User signIn(String username, String password);

    boolean isPasswordValid(String password);

    boolean isEmailValid(String email);
}
