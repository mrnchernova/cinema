package by.project.cinema.controller;

import by.project.cinema.model.User;

import java.util.List;

import static by.project.cinema.util.Constants.*;

public class AdminController {


    public static void run() {
        adminMenu();

    }

    public static void adminMenu() {


        while (!step.equals(0)) {
            System.out.println(ADMIN_MENU);
            try {
                step = sc.nextLine();
            } catch (Exception e) {
                sc.next();
            }


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
                    System.out.println("update user");
                    System.out.println("enter id user");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (userService.isExistUser(id)) {
                        User user = userService.getById(id);
                        System.out.println("enter new password");
                        String newPassword = sc.nextLine();
                        user.setPassword(newPassword);
                        userService.update(user);
                        System.out.println("user successfully updated");
                    } else {
                        System.out.println("user not found for update");
                    }

                }
                case "3" -> {
                    System.out.println("delete user");
                    System.out.println("enter id user");
                    int id = sc.nextInt();
                    if (userService.isExistUser(id)) {
                        if (userService.delete(id)) {
                            System.out.println("user successfully deleted");
                        }
                    } else {
                        System.out.println("not found user");
                    }
                }
                case "4" -> {
                    System.out.println("get user by id");
                    System.out.println("id user");
                    int id = sc.nextInt();
                    if (userService.isExistUser(id)) {
                        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD, EMAIL, ROLE);
                        System.out.println(userService.getById(id));
                    } else {
                        System.out.println("not found user");
                    }


                }
                case "5" -> {
                    System.out.println("get all users");
                    List<User> userList = userService.getUsers();
                    System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD, EMAIL, ROLE);
                    for (User u : userList) {

                        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
                    }
                }
                case "0" -> {
//                    sc.close();
//                    System.exit(0);
                    MainController.mainMenu();
                }
                default -> {
                    System.out.println("something goes wrong");
                }
            }
        }
    }

}


