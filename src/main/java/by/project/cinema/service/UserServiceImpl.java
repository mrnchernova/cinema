package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(User user) {
        return userRepository.create(user);
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public boolean isExistUser(int id) {
        return userRepository.isExistUser(id);
    }

    @Override
    public boolean isExistUserByUsername(String username) {
        return userRepository.isExistUserByUsername(username);
    }

    @Override
    public User signIn(String username, String password) {
        return userRepository.signIn(username, password);
    }

    @Override
    public boolean isPasswordValid(String password) {
        String emailRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[`~@#$%^&*(){}<>+=_-]).{6,20}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public boolean isEmailValid(String email) {
        String emailRegex = "^[\\w-.]{2,20}@[a-z0-9]{2,10}\\.[a-z]{2,5}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
