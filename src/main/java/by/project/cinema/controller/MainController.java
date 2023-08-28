package by.project.cinema.controller;


import by.project.cinema.model.User;

import static by.project.cinema.util.Constants.*;

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
                    User currentUser = userService.signIn(username, password);
                    if (currentUser == null) {
                        System.out.println(NEEDS_REGISTRATION);
                    } else {
                        System.out.println("\n" + WELCOME + username);
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> AdminController.adminMenu();
                            case "MANAGER" -> ManagerController.managerMenu();
                            case "USER" -> UserController.userMenu(currentUser);                   // надо передать юзера
                            default -> System.out.println(UNKNOWN_ROLE);
                        }
                    }
                }
                case "0" -> {
                    sc.close();
                    System.exit(0);
                }
                default -> {
                    System.out.println(SOMETHING_WRONG);
                }
            }
        }
    }

}
