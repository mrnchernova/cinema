package by.project.cinema.controller;

import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class MainController {

    public static void mainMenu() {
        log.info("MENU: Main");
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println('\n' + MAIN_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    log.info("MENU: List Of Movies");
                    movieService.movieInfo();
                }
                case "2" -> {
                    log.info("MENU: Registration");
                    userService.createUser();
                }

                case "3" -> {
                    log.info("MENU: Sign In");
                    System.out.print(ENTER_USERNAME);
                    String username = sc.nextLine();
                    System.out.print(ENTER_PASSWORD);
                    String password = sc.nextLine();

                    if (userService.signIn(username, password)) {
                        User currentUser = userService.getUserByUsername(username).orElse(null);
                        System.out.println("\n" + WELCOME + username);
                        assert currentUser != null;
                        log.info("User signed in. Username:" + username + " | email:" + currentUser.getEmail());
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> AdminController.adminMenu();
                            case "MANAGER" -> ManagerController.managerMenu(currentUser);
                            case "USER" -> UserController.userMenu(currentUser);
                            default -> System.out.println(UNKNOWN_ROLE);
                        }
                    } else {
                        System.out.println("Incorrect username or password");
                        log.warn("Incorrect username or password");
                    }
                }

                case "0" -> {
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

}
