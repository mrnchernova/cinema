package by.project.cinema.controller;

import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class MainController {

    public static void mainMenu() {
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println('\n' + MAIN_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    log.info("Select list of films");
                    movieService.movieInfo();
                }
                case "2" -> {
                    log.info("Select registration user");
                    userService.createUser();
                }
                case "3" -> {
                    log.info("Select sign in");
                    System.out.print(ENTER_USERNAME);
                    String username = sc.nextLine();
                    System.out.print(ENTER_PASSWORD);
                    String password = sc.nextLine();
                    User currentUser = userService.signIn(username, password);
                    if (currentUser == null) {
                        System.out.println(NEEDS_REGISTRATION);
                        log.warn("Login attempt with username: " + username);
                    } else {
                        System.out.println("\n" + WELCOME + username);
                        log.info("User signed in. Username:" + username + ", email: " + currentUser.getEmail());
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> {
                                log.info("Go to admin menu");
                                AdminController.adminMenu();
                            }
                            case "MANAGER" -> {
                                log.info("Go to manager menu");
                                ManagerController.managerMenu(currentUser);
                            }
                            case "USER" -> {
                                log.info("go to user menu");
                                UserController.userMenu(currentUser);
                            }
                            default -> {
                                System.out.println(UNKNOWN_ROLE);
                                log.error("Username: " + username + " has unknown role. ");
                            }
                        }
                    }
                }
                case "0" -> {
                    sc.close();
                    System.exit(0);
                }
                default -> {
                    System.out.println(SOMETHING_WRONG);
                    log.warn("Undefined command in main menu");
                }
            }
        }
    }

}
