package by.project.cinema.controller;

import by.project.cinema.model.User;
import by.project.cinema.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class UserController {

    public static void userMenu(User user) {
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println(USER_MENU);
            step = sc.nextLine();

            switch (step) {
                case "1" -> {
                    log.info("MENU: Order Ticket");
                    MovieController.movieMenuUser(user);
                }
                case "2" -> {
                    log.info("MENU: User Ticket");
                    TicketController.returnTicket(user, null);
                }
                case "3" -> {
                    log.info("MENU: Update Account");
                    System.out.println(UPDATE_ACCOUNT);
                    step = sc.nextLine();
                    switch (step) {
                        case "1" -> {
                            System.out.println(ENTER_PASSWORD);
                            String newPassword = sc.nextLine();
                            if (userService.isPasswordValid(newPassword)) {
                                EncryptPassword(user, newPassword);
                                if (userService.updateUser(user)) {
                                    System.out.println(SUCCESSFUL);
                                    log.info("User changed password successfully");
                                }
                            } else {
                                System.out.println(NOT_SUCCESSFUL + PASSWORD_NOT_VALID + PASSWORD_RULE);
                                log.error("User couldn't change password. Password not valid");
                            }

                        }
                        case "2" -> {
                            System.out.println(ENTER_EMAIL);
                            String newEmail = sc.nextLine();
                            if (userService.isEmailValid(newEmail)) {
                                user.setEmail(newEmail);
                                userService.updateUser(user);
                                System.out.println(SUCCESSFUL);
                                log.info("User changed email successfully");
                            } else {
                                System.out.println(NOT_SUCCESSFUL + EMAIL_NOT_VALID);
                                log.error("User couldn't change email. Email not valid");
                            }

                        }
                        case "0" -> UserController.userMenu(user);
                        default -> {
                            System.out.println(SOMETHING_WRONG);
                            UserController.userMenu(user);
                        }
                    }
                }
                case "0" -> {
                    log.info("Log out");
                    MainController.mainMenu();
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

    private static void EncryptPassword(User user, String newPassword) {
        byte[] salt = PasswordEncrypt.generateSalt(user.getUsername());
        byte[] encryptedPassword = PasswordEncrypt.getEncryptedPassword(newPassword, salt);
        try {
            user.setPassword(new String(encryptedPassword, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
