package by.project.cinema.controller;

import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class MainController {

    public static void mainMenu() {
        log.info(MENU_MAIN);
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println('\n' + MAIN_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    log.info(MENU_LIST_OF_MOVIES);
                    movieService.getMovies();
                }
                case "2" -> {
                    log.info(MENU_REGISTRATION);
                    userService.createUser();
                }

                case "3" -> {
                    log.info(MENU_LOG_IN);
                    System.out.print(ENTER_USERNAME);
                    String username = sc.nextLine();
                    System.out.print(ENTER_PASSWORD);
                    String password = sc.nextLine();

                    if (userService.signIn(username, password)) {
                        User currentUser = userService.getUserByUsername(username).orElse(null);
                        System.out.println("\n" + WELCOME + username);
                        assert currentUser != null;
                        log.info(USER_LOGGED_IN + _USERNAME + username + _EMAIL + currentUser.getEmail());
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> AdminController.adminMenu();
                            case "MANAGER" -> ManagerController.managerMenu(currentUser);
                            case "USER" -> UserController.userMenu(currentUser);
                            default -> System.out.println(UNKNOWN_ROLE);
                        }
                    } else {
                        System.out.println(USER_INCORRECT_USERNAME_OR_PASSWORD);
                        log.warn(USER_INCORRECT_USERNAME_OR_PASSWORD);
                    }
                }

                case "0" -> {
                    log.info(EXIT);
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

}
