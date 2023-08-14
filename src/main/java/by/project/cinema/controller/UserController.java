package by.project.cinema.controller;

import static by.project.cinema.util.Constants.*;

public class UserController {

    public static void userMenu() {
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
