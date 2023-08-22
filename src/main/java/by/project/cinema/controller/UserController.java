package by.project.cinema.controller;

import by.project.cinema.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    System.out.println(UPDATE_ACCOUNT);
                    step = sc.nextLine();
                    switch (step) {
                        case "1" -> {
                            System.out.println("enter new password");
                            String newPassword = sc.nextLine();
                            if (isPasswordValid(newPassword)) {
                                user.setPassword(newPassword);
                                if (userService.updateUser(user)) {
                                    System.out.println("password changed");
                                } else {
                                    System.out.println("password not changed");
                                }
                            } else {
                                System.out.println("The password must consist of a capital letter, a lowercase letter, a number, and a symbol. Password length [6..20]");
                            }
//
                        }
                        case "2" -> {
                            System.out.println("enter new email");
                            String newEmail = sc.nextLine();
                            if (isEmailValid(newEmail)) {
                                user.setEmail(newEmail);
                                userService.updateUser(user);
                            } else {
                                System.out.println("email not valid");
                            }

                        }
                        case "0" -> UserController.userMenu(user);
                        default -> {
                            System.out.println("something goes wrong");
                            UserController.userMenu(user);
                        }
                    }

                }
                case "0" -> MainController.mainMenu();
                default -> System.out.println("something goes wrong");
            }

        }
    }

                                                                                                                        // TODO запихнуть в userRepository
    public static boolean isPasswordValid(String password) {
        String emailRegex = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[`~@#$%^&*(){}<>+=_-]).{6,20}";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isEmailValid(String email) {
        String emailRegex = "^[\\w-.]{2,20}@[a-z0-9]{2,10}\\.[a-z]{2,5}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
