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
        movieService.movieInfo();                   //list of films
        int movieId = selectMovie();                //film id

        if (movieService.getById(movieId).isEmpty()) {
            System.out.format("фильм по id %d не найден", movieId);
        } else {
            System.out.println("\nFilm: " + movieService.getById(movieId).get());  // Optional
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
                                    System.out.println("ticket bought by " + user.getUsername());
                                    System.out.println("seat " + cinemaSeat);
                                    System.out.println("movie " + movieService.getById(movieId).get().getTitle());
                                }
                                break;
                            }
                        }
                    }                               //buy ticket
                    case "0" -> UserController.userMenu(user);      //back
                    default -> System.out.println(SOMETHING_WRONG);
                }
            } else {
                System.out.println("\n" + TICKET_SOLD_OUT);
                UserController.userMenu(user);
            }
        }
    }

    public static void movieMenuManager() {
        movieService.movieInfo();
        System.out.println(MANAGER_MOVIE_MENU);
        step = sc.nextLine();
        switch (step) {
            case "1" -> {
                System.out.println(TICKET_BUY_FOR_USER);
            }
            case "2" -> {
                System.out.println(TICKET_RETURN_FOR_USER);
            }
            case "0" -> ManagerController.managerMenu();
            default -> System.out.println(SOMETHING_WRONG);
        }


    }


    public static int selectMovie() {
        System.out.println(ENTER_MOVIE_ID);
        int movieId = sc.nextInt();
        sc.nextLine();// чтоб в меню не считывалась пустая строка
        Movie m = movieService.getById(movieId)
//                .orElseThrow(() -> new NoSuchElementException(String.format("фильм по id %d не найден", movieId)));
                .orElse(new Movie());
//        System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().getTime()));
//        System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().toLocalDate()));   // !!! date
        return movieId;
    }


}


