package by.project.cinema.controller;

import by.project.cinema.model.User;

import static by.project.cinema.util.Constants.*;

public class UserController {

    public static void userMenu(User user) {
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println(USER_MENU);
            step = sc.nextLine();

            switch (step) {
                case "1" -> {
                    System.out.println("list of films");
                    MovieController.movieMenuUser(user);
                }
                case "2" -> {
                    System.out.println("your tickets");
                    TicketController.ticketMenuUser(user);
                }
                case "3" -> {
                    System.out.println("update account");
                }
                case "0" -> MainController.mainMenu();
                default -> System.out.println("something goes wrong");
            }

        }
    }
}
