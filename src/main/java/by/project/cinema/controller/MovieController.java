package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.User;

import java.util.InputMismatchException;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

public class MovieController {

    public static void movieMenuUser(User user) {
        TicketController.orderTicket(user, null);                                                               //manager field needs for returning to necessary menu
    }

    public static void movieMenuManager(User manager) {
        movieService.movieInfo();
        System.out.println(MANAGER_MOVIE_MENU);
        step = sc.nextLine();
        switch (step) {
            case "1" -> {
                System.out.println(TICKET_BUY_FOR_USER);
                userService.getUsers();
                User selectedUser = checkForValidUserId();
                TicketController.orderTicket(selectedUser, manager);
            }                                                                                           //buy ticket for user


            case "2" -> {
                System.out.println(TICKET_RETURN_FOR_USER);
                userService.getUsers();
                User selectedUser = checkForValidUserId();
                TicketController.returnTicket(selectedUser, manager);
            }                                                                                                           //return ticket for user

            case "0" -> ManagerController.managerMenu(manager);
            default -> System.out.println(SOMETHING_WRONG);
        }
    }

    public static int selectMovie() {
        System.out.println(ENTER_MOVIE_ID);
       try{
           int movieId = sc.nextInt();
           sc.nextLine();
           Movie m = movieService.getMovieById(movieId)
                   .orElse(new Movie());
           return movieId;
       }catch (InputMismatchException e) {
           sc.next();
           selectMovie();
       }
       return 0;
    }

    public static User checkForValidUserId(){
        System.out.println("\nSelect user id");
        boolean validId = false;
        while (!validId) {
            try {
                int userId = sc.nextInt();
                sc.nextLine();
                if (userService.isExistUser(userId)) {
                    validId = true;
                    return userService.getUserById(userId);
                } else {
                    validId = false;
                    System.out.println("User not found. Try again");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter user id ");
                validId = false;
                sc.next();
            }
        }
        return new User();
    }

}


