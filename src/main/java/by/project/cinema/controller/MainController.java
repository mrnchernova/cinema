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
                    System.out.println("registration");
                    System.out.print("login ");
                    String username = sc.nextLine();
                    System.out.print("password ");
                    String password = sc.nextLine();
                    System.out.print("email "); //TODO validation email
                    String email = sc.nextLine();

                    User user = new User(username, password, email);
                    userService.create(user);
                }
                case "3" -> {
                    System.out.println("sign in");
                    System.out.print("Enter username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter password: ");
                    String password = sc.nextLine();
                    User currentUser = userService.signIn(username, password);
                    if (currentUser == null) {
                        System.out.println("you need registered first");
                    } else {
                        System.out.println("\nwelcome, " + username);
                        switch (currentUser.getRole().toString()) {
                            case "ADMIN" -> AdminController.adminMenu();
                            case "MANAGER" -> ManagerController.managerMenu();
                            case "USER" -> UserController.userMenu(currentUser);                   // надо передать юзера
                            default -> System.out.println("Undefined role");
                        }
                    }
                } //sign in
                case "0" -> {
                    sc.close();
                    System.exit(0);
                } //close
                default -> {
                    System.out.println("something goes wrong");
                }
            }
        }
    }

}
