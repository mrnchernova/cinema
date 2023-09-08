package by.project.cinema.controller;

import by.project.cinema.model.User;
import by.project.cinema.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;

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
                    log.info(MENU_ORDER_TICKET);
                    MovieController.movieMenuUser(user);
                }
                case "2" -> {
                    log.info(MENU_USER_TICKET);
                    TicketController.returnTicket(user, null);
                }
                case "3" -> {
                    log.info(MENU_UPDATE_ACCOUNT);
                    System.out.println(UPDATE_ACCOUNT);
                    step = sc.nextLine();
                    switch (step) {
                        case "1" -> {
                            System.out.println(ENTER_PASSWORD);
                            String newPassword = sc.nextLine();
                            if (userService.isPasswordValid(newPassword)) {
                                user.setPassword(PasswordEncrypt.EncryptPassword(user.getUsername(), newPassword));
                                if (userService.updateUser(user)) {
                                    System.out.println(USER_CHANGED_PASSWORD);
                                    log.info(USER_CHANGED_PASSWORD);
                                }
                            } else {
                                System.out.println(NOT_SUCCESSFUL + PASSWORD_NOT_VALID + PASSWORD_RULE);
                                log.error(USER_NOT_CHANGED_PASSWORD);
                            }

                        }
                        case "2" -> {
                            System.out.println(ENTER_EMAIL);
                            String newEmail = sc.nextLine();
                            if (userService.isEmailValid(newEmail)) {
                                user.setEmail(newEmail);
                                userService.updateUser(user);
                                System.out.println(SUCCESSFUL);
                                log.info(USER_CHANGED_EMAIL);
                            } else {
                                System.out.println(NOT_SUCCESSFUL + EMAIL_NOT_VALID);
                                log.error(USER_NOT_CHANGED_EMAIL);
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
                    log.info(LOG_OUT);
                    MainController.mainMenu();
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }
}
