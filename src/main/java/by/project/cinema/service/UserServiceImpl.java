package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.sc;
import static by.project.cinema.util.Util.userService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser() {
        System.out.print(ENTER_USERNAME);
        String username = sc.nextLine();
        while ((userService.isExistUserByUsername(username)) || (username.isEmpty())) {
            System.out.println(USER_EXISTS + TRY_AGAIN);
            username = sc.nextLine();
        }

        System.out.print(ENTER_PASSWORD);
        String password = sc.nextLine();
        while ((!userService.isPasswordValid(password) || (password.isEmpty()))) {
            System.out.println(PASSWORD_NOT_VALID + PASSWORD_RULE + TRY_AGAIN);
            password = sc.nextLine();
        }

        System.out.print(ENTER_EMAIL);
        String email = sc.nextLine();
        while ((!userService.isEmailValid(email)) || (email.isEmpty())) {
            System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
            email = sc.nextLine();
        }

        User user = new User(username, password, email);
        userRepository.createUser(user);
    }

    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public void getUsers() {
//        return userRepository.getUsers();
        List<User> userList = userRepository.getUsers();
        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD, EMAIL, ROLE);
        for (User u : userList) {
            System.out.format("\n%-4s %-15s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
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
