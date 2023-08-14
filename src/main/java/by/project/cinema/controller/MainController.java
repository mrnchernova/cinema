package by.project.cinema.controller;


import by.project.cinema.model.User;

import static by.project.cinema.util.Constants.*;

public class MainController {

    public static void mainMenu() {
        System.out.println(MAIN_MENU);
        step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.println("list of films");
            }
            case "2" -> {
                System.out.println("registration");
                System.out.print("login ");
                String username = sc.nextLine();
                System.out.print("password ");
                String password = sc.nextLine();
                System.out.print("email ");
                String email = sc.nextLine();

                User user = new User(username, password, email);
                userService.create(user);
            }
            case "3" -> {
                System.out.println("sign in");
                UserController.userMenu();
            }
            case "0" -> {
                sc.close();
                System.exit(0);
            }
            case "admin" -> {                       // secret command
               AdminController.adminMenu();
            }
            default -> {
                System.out.println("something goes wrong");
                sc.close();
                System.exit(0);
            }
        }
    }





}
