package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import static by.project.cinema.util.Constants.*;

public class MovieController {
    public static DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    public static void movieMenuUser(User user) {
        orderTicket(user, null);                                                                                //manager field needs for returning to necessary menu
    }

    public static void movieMenuManager(User manager) {
        movieService.movieInfo();
        System.out.println(MANAGER_MOVIE_MENU);
        step = sc.nextLine();
        switch (step) {
            case "1" -> {
                System.out.println(TICKET_BUY_FOR_USER);
                userService.getUsers();
                System.out.println("\nSelect user id");
                int userId = sc.nextInt();
                sc.nextLine();

                User selectedUser = userService.getUserById(userId);
                orderTicket(selectedUser, manager);
            }                                                                                           //buy ticket for user
            case "2" -> {
                System.out.println(TICKET_RETURN_FOR_USER);

                userService.getUsers();
                System.out.println("\nSelect user id");
                int userId = sc.nextInt();
                sc.nextLine();

                User selectedUser = userService.getUserById(userId);
                TicketController.ticketMenuUser(selectedUser, manager);
            }                                                                                           //return ticket for user
            case "0" -> ManagerController.managerMenu(manager);
            default -> System.out.println(SOMETHING_WRONG);
        }
    }

    public static int selectMovie() {
        System.out.println(ENTER_MOVIE_ID);
        int movieId = sc.nextInt();
        sc.nextLine();
        Movie m = movieService.getMovieById(movieId)
                .orElse(new Movie());
        return movieId;
    }

    public static void orderTicket(User user, User manager) {
        movieService.movieInfo();                                                                                       //list of films
        int movieId = selectMovie();                                                                                    //film id

        if (movieService.getMovieById(movieId).isEmpty()) {
            System.out.format("Movie id %d not found", movieId);
        } else {
            System.out.println("\nFilm: " + movieService.getMovieById(movieId).get());                                  // Optional
            System.out.print("Available tickets: " + ticketService.countOfAvailableTickets(movieId));
            if (ticketService.countOfAvailableTickets(movieId) != 0) {

                System.out.println('\n' + USER_MOVIE_MENU);
                step = sc.nextLine();

                switch (step) {
                    case "1" -> {
                        System.out.println(TICKET_SEAT);
                        List<Ticket> notReservedTickets = ticketService.listOfAvailableTickets(movieId);

                        System.out.format("%-4s %-10s\n", SEAT, PRICE);
                        for (Ticket t : notReservedTickets) {
                            System.out.format("%-4s %-10s\n", t.getSeat(), t.getPrice());
                        }
                        int cinemaSeat = sc.nextInt();
                        sc.nextLine();

                        for (Ticket t : notReservedTickets) {
                            if (cinemaSeat == t.getSeat()) {
                                if (ticketService.reserveTicket(user, cinemaSeat, movieId)) {
                                    System.out.println("ticket ordered for user: " + user.getUsername());
                                    System.out.println("seat: " + cinemaSeat);
                                    System.out.println("movie: " + movieService.getMovieById(movieId).get().getTitle());
                                }
                                break;
                            }
                        }
                    }                                                                                   //buy ticket
                    case "0" -> {
                        if (manager == null) {
                            UserController.userMenu(user);
                        } else {
                            ManagerController.managerMenu(manager);
                        }
                    }                                                                                   //back
                    default -> System.out.println(SOMETHING_WRONG);
                }
            } else {
                System.out.println("\n" + TICKET_SOLD_OUT);
                UserController.userMenu(user);
            }
        }
    }
}


