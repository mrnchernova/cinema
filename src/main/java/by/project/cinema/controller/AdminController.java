package by.project.cinema.controller;

import by.project.cinema.model.User;

import java.util.List;

import static by.project.cinema.util.Constants.*;

public class AdminController {

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
//                    System.out.println("create user");
                    System.out.println(ENTER_USERNAME);//TODO validation
                    String username = sc.nextLine();
                    System.out.println(ENTER_PASSWORD);
                    String password = sc.nextLine();
                    System.out.println(ENTER_EMAIL);
                    String email = sc.nextLine();

                    User user = new User(username, password, email);
                    userService.create(user);
                }
                case "2" -> {                                       // TODO админ может менять ВСех!!! и роли тоже
//                    System.out.println("update user");
                    System.out.println(ENTER_USER_ID);
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (userService.isExistUser(id)) {
                        User user = userService.getById(id);
                        System.out.println(ENTER_PASSWORD);
                        String newPassword = sc.nextLine();
                        user.setPassword(newPassword);
//                        userService.updatePersonPassword(user);//TODO что это?
                        System.out.println(USER_UPDATED);
                    } else {
                        System.out.println(USER_NOT_FOUND);
                    }

                }
                case "3" -> {
//                    System.out.println("delete user");
                    System.out.println(ENTER_USER_ID);
                    int id = sc.nextInt();
                    if (userService.isExistUser(id)) {
                        if (userService.delete(id)) {
                            System.out.println(USER_DELETED);
                        }
                    } else {
                        System.out.println(USER_NOT_FOUND);
                    }
                }
                case "4" -> {
//                    System.out.println("get user by id");
                    System.out.println(ENTER_USER_ID);
                    int id = sc.nextInt();
                    if (userService.isExistUser(id)) {
                        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD, EMAIL, ROLE);
                        System.out.println(userService.getById(id));
                    } else {
                        System.out.println(USER_NOT_FOUND);
                    }


                }
                case "5" -> {
//                    System.out.println("get all users");
                    List<User> userList = userService.getUsers();
                    System.out.format("\n%-4s %-15s %-15s %-15s %-10s", ID, USERNAME, PASSWORD, EMAIL, ROLE);
                    for (User u : userList) {
                        System.out.format("\n%-4s %-15s %-15s %-15s %-10s", u.getId(), u.getUsername(), u.getPassword(), u.getEmail(), u.getRole());
                    }
                }
                case "0" -> {
                    MainController.mainMenu();
                }
                default -> {
                    System.out.println(SOMETHING_WRONG);
                }
            }
        }
    }

}


