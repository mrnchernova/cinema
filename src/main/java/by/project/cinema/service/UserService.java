package by.project.cinema.service;

import by.project.cinema.model.User;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;


public interface UserService {
    void createUser();

    boolean updateUser(User user);

    boolean delete(int id);

    void getUsers();

    User getUserById(int id);

    Optional<User> getUserByUsername(String username);

    boolean isExistUser(int id);

    boolean isExistUserByUsername(String username);

    boolean signIn(String username, String password);

    boolean isPasswordValid(String password);

    boolean isEmailValid(String email);
}
