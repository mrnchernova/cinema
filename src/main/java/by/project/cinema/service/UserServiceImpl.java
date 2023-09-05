package by.project.cinema.service;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;
import by.project.cinema.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.sc;
import static by.project.cinema.util.Util.userService;

@Slf4j
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

        /**
         * Encrypt password 
         */
        String reverseUsername = new StringBuilder(username).reverse().toString();
        byte[] byteReverseUsername = reverseUsername.getBytes();
        byte[] encryptedPassword = new byte[0]; //todo что ты такое?
        try {
            encryptedPassword = PasswordEncrypt.getEncryptedPassword(password, byteReverseUsername);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("Error in encrypt password");
            log.error("Error in encrypt password");
            e.printStackTrace();
        }
        try {
            password = new String(encryptedPassword, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error in encoding UTF-8");
            log.error("Error in encoding UTF-8");
            e.printStackTrace();
        }
        /** Encrypt password */


        System.out.print(ENTER_EMAIL);
        String email = sc.nextLine();
        while ((!userService.isEmailValid(email)) || (email.isEmpty())) {
            System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
            email = sc.nextLine();
        }

        User user = new User(username, password, email);
        if (userRepository.createUser(user)) {
            log.info("Created user: " + username + " " + email);
        } else {
            log.error("User not created");
        }
    }

    public boolean updateUser(User user) {
        /**
         * Encrypt password 
         */
        String reverseUsername = new StringBuilder(user.getUsername()).reverse().toString();
        byte[] byteReverseUsername = reverseUsername.getBytes();
        byte[] encryptedPassword = new byte[0];
        try {
            encryptedPassword = PasswordEncrypt.getEncryptedPassword(user.getPassword(), byteReverseUsername);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("Error in encrypt password");
            log.error("Error in encrypt password");
            e.printStackTrace();
        }
        try {
            user.setPassword(new String(encryptedPassword, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error in encoding UTF-8");
            log.error("Error in encoding UTF-8");
            e.printStackTrace();
        }
        /** Encrypt password */
        return userRepository.updateUser(user);
    }

    @Override
    public boolean delete(int id) {
        return userRepository.delete(id);
    }

    @Override
    public void getUsers() {
        List<User> userList = userRepository.getUsers();
        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, EMAIL, ROLE);
        for (User u : userList) {
            System.out.format("\n%-4s %-15s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getEmail(), u.getRole());
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


    
//    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Override
    public boolean signIn(String username, String notEncryptedPassword) {
        
        byte[] passwordInDB = userRepository.getUserByUsername(username).getPassword().getBytes();

        //getSalt
        String reverseUsername = new StringBuilder(username).reverse().toString();
        byte[] salt = reverseUsername.getBytes();

        if (PasswordEncrypt.authenticate(notEncryptedPassword, passwordInDB, salt)) {
            System.out.println("passwords equal");
            return true;
//            return userRepository.signIn(username, passwordInDB);
        } else {
            System.out.println("passwords not equal");
            return false;
        }
    


//        try {
//            if (PasswordEncrypt.authenticate(notEncryptedPassword, passwordInDB, salt)) {
//                String password = null;
//                try {
//                    password = new String(passwordInDB, "UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                return userRepository.signIn(username, password);
//            } else System.out.println("passwords not equal");
//
//        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//
//        return false;
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
