package by.project.cinema.controller;

import by.project.cinema.model.User;
import by.project.cinema.repository.UserRepository;
import by.project.cinema.repository.UserRepositoryImpl;
import by.project.cinema.service.UserService;
import by.project.cinema.service.UserServiceImpl;

import java.util.Scanner;

public class MainController {

    private static Scanner sc = new Scanner(System.in);
    private static UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    public static final String MENU = """
            1. registration user
            2. get all users
            0. exit
            """;

    //System.out.format("\n%-3s %-30s %-30s %-10s", ID, USERNAME, PASSWORD, ROLE); // заголовки для вывода всех записей
    public static void run() {

        System.out.println(MENU);

        String step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.print("login ");
                String username = sc.nextLine();
                System.out.print("password ");
                String password = sc.nextLine();
                System.out.print("email ");
                String email = sc.nextLine();

                User user = new User(username, password, email);
                userService.create(user);
            }
            case "2" -> {
                System.out.println("all users here:");

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
