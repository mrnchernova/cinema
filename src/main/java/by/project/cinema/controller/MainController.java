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
                case "2" -> {
                    System.out.print(ENTER_USERNAME);
                    String username = sc.nextLine();
                    while (userService.isExistUserByUsername(username)){
                        System.out.println(USER_EXISTS + TRY_AGAIN);
                        username = sc.nextLine();
                    }

                    System.out.print(ENTER_PASSWORD);
                    String password = sc.nextLine();
                    while (!userService.isPasswordValid(password)){
                        System.out.println(PASSWORD_NOT_VALID + PASSWORD_RULE + TRY_AGAIN);
                        password = sc.nextLine();
                    }


                    System.out.print(ENTER_EMAIL);
                    String email = sc.nextLine();
                    while (!userService.isEmailValid(email)){
                        System.out.println(EMAIL_NOT_VALID + TRY_AGAIN);
                        email = sc.nextLine();
                    }

                    User user = new User(username, password, email);
                    userService.create(user);


                }
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
