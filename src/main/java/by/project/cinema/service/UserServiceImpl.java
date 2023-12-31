package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;
import by.project.cinema.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.sc;
import static by.project.cinema.util.Util.userService;

@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
        String encryptedPassword = PasswordEncrypt.EncryptPassword(username, password);

        System.out.print(ENTER_EMAIL);
        String email = sc.nextLine();
        while ((!userService.isEmailValid(email)) || (email.isEmpty())) {
            System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
            email = sc.nextLine();
        }

        User user = new User(username, encryptedPassword, email);
        if (userRepository.createUser(user)) {
            System.out.println(USER_CREATED);
            log.info(USER_CREATED_LOG + username + " | " + email);
        } else {
            System.out.println(USER_NOT_CREATED);
            log.error(USER_NOT_CREATED);
        }
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
        List<User> userList = userRepository.getUsers();
        System.out.format("\n%-4s %-15s %-15s %-10s", ID, USERNAME, EMAIL, ROLE);
        for (User u : userList) {
            System.out.format("\n%-4s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getEmail(), u.getRole());
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.ofNullable(userRepository.getUserByUsername(username));
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
    public boolean signIn(String username, String notEncryptedPassword) {
        if (!username.isEmpty()) {
            if (userRepository.isExistUserByUsername(username)) {
                byte[] passwordInDB = new byte[0];
                try {
                    passwordInDB = userRepository.getUserByUsername(username).getPassword().getBytes("windows-1251");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                byte[] salt = PasswordEncrypt.generateSalt(username);
                return PasswordEncrypt.authenticate(notEncryptedPassword, passwordInDB, salt);
            }
        }
        return false;
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
