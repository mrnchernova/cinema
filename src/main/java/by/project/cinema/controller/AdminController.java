package by.project.cinema.controller;

import by.project.cinema.model.Role;
import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class AdminController {

    public static void adminMenu() {

        while (!step.equals(0)) {
            System.out.println(ADMIN_MENU);
            try {
                step = sc.nextLine();
            } catch (Exception e) {
                sc.next();
            }

            switch (step) {
                case "1" -> {
                    userService.createUser();                                                                   //create user
                }
                case "2" -> {
                    System.out.println(ENTER_USER_ID);
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (userService.isExistUser(id)) {
                            User user = userService.getUserById(id);

                            System.out.println(USER_NEW_USERNAME);
                            String username = sc.nextLine();
                            if (!username.isEmpty()) {
                                while (userService.isExistUserByUsername(username)) {
                                    System.out.println(USER_EXISTS + TRY_AGAIN);
                                    username = sc.nextLine();
                                }
                                user.setUsername(username);
                            }

                            System.out.println(USER_NEW_PASSWORD);
                            String password = sc.nextLine();
                            if (!password.isEmpty()) {
                                while (!userService.isPasswordValid(password)) {
                                    System.out.println(PASSWORD_NOT_VALID + PASSWORD_RULE + TRY_AGAIN);
                                    password = sc.nextLine();
                                }
                                user.setPassword(password);
                            }

                            System.out.println(USER_NEW_EMAIL);
                            String email = sc.nextLine();
                            if (!email.isEmpty()) {
                                while (!userService.isEmailValid(email)) {
                                    System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
                                    email = sc.nextLine();
                                }
                                user.setEmail(email);
                            }

                            System.out.println(USER_NEW_ROLE);
                            int roleId = sc.nextInt();
                            sc.nextLine();
                            if (roleId == 1 || roleId == 2 || roleId == 3) {
                                user.setRole(Role.getByOrdinal(--roleId));
                            }
                            if (userService.updateUser(user)) {
                                System.out.println(USER_UPDATED);
                                log.info("Username updated successfully. New data:" + user.getUsername() + " " + user.getEmail() + " " + user.getRole());
                            } else {
                                System.out.println(NOT_SUCCESSFUL);
                                log.error("Unsuccessful update user " + user.getUsername());
                            }
                        } else {
                            System.out.println("Unknown user id=" + id);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Unknown user id");
                        sc.nextLine();
                    }
                }                                                                                       //update user
                case "3" -> {
                    System.out.println(ENTER_USER_ID);
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (userService.isExistUser(id)) {
                            if (userService.delete(id)) {
                                System.out.println(USER_DELETED);
                                log.info("User id=" + id + " was successfully deleted");
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
                }                                                                                       //delete user

                case "4" -> {
                    userService.getUsers();                                                                     //get all users
                }
                case "0" -> {
                    MainController.mainMenu();
                }
                default -> {
                    System.out.println(SOMETHING_WRONG);
                }
            }
        }
    }

}


