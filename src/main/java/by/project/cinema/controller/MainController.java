package by.project.cinema.controller;

import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
                case "1" -> movieService.movieInfo();
                case "2" -> userService.createUser();


                case "3" -> {
                    System.out.print(ENTER_USERNAME);
                    String username = sc.nextLine();
                    System.out.print(ENTER_PASSWORD);
                    String password = sc.nextLine();

                    //boolean
                    if (userService.signIn(username, password)) {
                        System.out.println("ok");
                    } else System.out.println("ne ok");
                    
 /*                    User currentUser = userService.signIn(username, password);
                    if (currentUser == null) {
                        System.out.println(NEEDS_REGISTRATION);
                        log.warn("Incorrect username or password entered");
                    } else {
                        System.out.println("\n" + WELCOME + username);
                        log.info("User signed in. Username:" + username + ", email: " + currentUser.getEmail());
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> AdminController.adminMenu();
                            case "MANAGER" -> ManagerController.managerMenu(currentUser);
                            case "USER" -> UserController.userMenu(currentUser);
                            default -> System.out.println(UNKNOWN_ROLE);
                        }
                    }
*/


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
