package by.project.cinema.controller;

import by.project.cinema.model.Role;
import by.project.cinema.model.User;
import by.project.cinema.util.PasswordEncrypt;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class AdminController {

    public static void adminMenu() {
        while (!step.equals(0)) {
            System.out.println(ADMIN_MENU);
            step = sc.nextLine();

            switch (step) {
                case "1" -> {
                    log.info("MENU: Create User");
                    userService.createUser();
                }
                case "2" -> {
                    log.info("MENU: Update User");
                    System.out.println(ENTER_USER_ID);
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (userService.isExistUser(id)) {
                            User user = userService.getUserById(id);

                            newPassword(user);
                            newEmail(user);
                            newRole(user);

                            if (userService.updateUser(user)) {
                                System.out.println(USER_UPDATED);
                                log.info("Username updated successfully. New data:" + user.getUsername() + " | " + user.getEmail() + " | " + user.getRole());
                            } else {
                                System.out.println(NOT_SUCCESSFUL);
                                log.error("Unsuccessful update user:" + user.getUsername());
                            }
                        } else {
                            System.out.println("Unknown user id:" + id);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Unknown user id");
                        sc.nextLine();
                    }
                }
                case "3" -> {
                    log.info("MENU: Delete User");
                    System.out.println(ENTER_USER_ID);
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (userService.isExistUser(id)) {
                            if (userService.delete(id)) {
                                System.out.println(USER_DELETED);
                                log.info("User id=" + id + " successfully deleted");
                            }
                        } else {
                            System.out.println(USER_NOT_FOUND);
                            log.warn(USER_NOT_FOUND);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(USER_NOT_FOUND);
                        log.warn(USER_NOT_FOUND);
                        sc.nextLine();
                    }
                }
                case "4" -> {
                    log.info("MENU: List Of Users");
                    userService.getUsers();
                }
                case "0" -> {
                    log.info("Log out");
                    MainController.mainMenu();
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

    private static void newRole(User user) {
        System.out.println(USER_NEW_ROLE);
        int roleId = sc.nextInt();
        sc.nextLine();
        if (roleId == 1 || roleId == 2 || roleId == 3) {
            user.setRole(Role.getByOrdinal(--roleId));
        }
    }

    private static void newEmail(User user) {
        System.out.println(USER_NEW_EMAIL_OR_SKIP);
        String email = sc.nextLine();
        if (!email.isEmpty()) {
            while (!userService.isEmailValid(email)) {
                System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
                email = sc.nextLine();
            }
            user.setEmail(email);
        }
    }

    private static void newPassword(User user) {
        System.out.println(USER_NEW_PASSWORD_OR_SKIP);
        String password = sc.nextLine();
        if (!password.isEmpty()) {
            while (!userService.isPasswordValid(password)) {
                System.out.println(PASSWORD_NOT_VALID + PASSWORD_RULE + TRY_AGAIN);
                password = sc.nextLine();
            }
            user.setPassword(PasswordEncrypt.EncryptPassword(user.getUsername(), password));
        }
    }
}


