package by.project.cinema.controller;

import static by.project.cinema.util.Constants.*;

public class ManagerController {

    public static void managerMenu() {

        System.out.println(MANAGER_MENU);
        step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.println("create film");
            }
            case "2" -> {
                System.out.println("update film");
            }
            case "3" -> {
                System.out.println("delete film");
            }
            case "4" -> {
                System.out.println("get film by id");
            }
            case "5" -> {
                System.out.println("get all films"); // DATE!!!
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