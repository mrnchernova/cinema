package by.project.cinema.controller;

import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class MovieController {

    public static void movieMenuUser(User user) {
        TicketController.orderTicket(user, null);                                                               //manager field needs for returning to necessary menu
    }

    public static void movieMenuManager(User manager) {
        movieService.getMovies();
        System.out.println(MANAGER_MOVIE_MENU);
        step = sc.nextLine();
        switch (step) {
            case "1" -> {
                log.info(MENU_BUY_TICKET_FOR_USER);
                System.out.println(TICKET_BUY_FOR_USER);
                userService.getUsers();
                User selectedUser = checkForValidUserId();
                TicketController.orderTicket(selectedUser, manager);
            }                                                                                           


            case "2" -> {
                log.info(MENU_RETURN_TICKET_FOR_USER);
                System.out.println(TICKET_RETURN_FOR_USER);
                userService.getUsers();
                User selectedUser = checkForValidUserId();
                TicketController.returnTicket(selectedUser, manager);
            }                                                                                                           

            case "0" -> ManagerController.managerMenu(manager);
            default -> System.out.println(SOMETHING_WRONG);
        }
    }

    public static User checkForValidUserId() {
        System.out.println(ENTER_USER_ID);
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
                    System.out.println(USER_NOT_FOUND_TRY_AGAIN);
                }
            } catch (InputMismatchException e) {
                System.out.println(ENTER_USER_ID);
                validId = false;
                sc.next();
            }
        }
        return new User();
    }
}


