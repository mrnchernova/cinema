package by.project.cinema.controller;


import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepositoryImpl;
import by.project.cinema.service.UserService;
import by.project.cinema.service.UserServiceImpl;

import java.util.List;

import static by.project.cinema.util.Constants.*;

public class MainController {
    private static String step = "-1";
    public static UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public static void run() {
//        mainMenu();
        adminMenu();
    }


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
                userMenu();
            }
            case "0" -> {
                sc.close();
                System.exit(0);
            }
            default -> {
                System.out.println("something goes wrong");
                sc.close();
                System.exit(0);
            }
        }
    }

    public static void userMenu() {
        System.out.println("Welcome username!");
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
                mainMenu();
            }
            default -> {
                System.out.println("something goes wrong");
                sc.close();
                System.exit(0);
            }
        }
    }

    public static void adminMenu() {
        System.out.println(ADMIN_MENU);
        step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.println("create user");
                String username = sc.nextLine();
                String password = sc.nextLine();
                String email = sc.nextLine();

                User user = new User(username, password, email);
                userService.create(user);
            }
            case "2" -> {
                System.out.println("---");
            }
            case "3" -> {
                System.out.println("delete user");
                System.out.println("id user");
                int id = sc.nextInt();
                userService.delete(id);
            }
            case "4" -> {
                System.out.println("get user by id");
                System.out.println("id user");
                int id = sc.nextInt();
                System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD,EMAIL, ROLE);
                System.out.println(userService.getById(id));


            }
            case "5" -> {
                System.out.println("get all users");
                List<User> userList = userService.getUsers();
                System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD,EMAIL, ROLE);
                for (User u: userList) {

                    System.out.format("\n%-4s %-15s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getPassword(),u.getEmail(), u.getRole());
                }
            }
            case "0" -> {
                sc.close();
                System.exit(0);
            }
            default -> {
                System.out.println("something goes wrong");
                sc.close();
                System.exit(0);
            }
        }
    }


}
