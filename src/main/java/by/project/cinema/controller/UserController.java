package by.project.cinema.controller;

import by.project.cinema.model.User;

import static by.project.cinema.util.Constants.*;

public class UserController {

    public static void userMenu() {

        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User currentUser = userService.signIn(username, password);
        if (currentUser == null) {
            System.out.println("you need registered first");
        } else {
            System.out.println("welcome, " + username);



        System.out.println(USER_MENU);
        step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.println("list of films");
            }
            case "2" -> {
                System.out.println("your tickets");
            }
            case "3" -> {
                System.out.println("update account");
            }
            case "0" -> {
                MainController.mainMenu();
            }
            default -> {
                System.out.println("something goes wrong");
                sc.close();
                System.exit(0);
            }
        }
        }
    }
}
