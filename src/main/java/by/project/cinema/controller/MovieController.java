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
        movieService.movieInfo();

        int movieId = selectMovie();

        System.out.println("\nFilm: " + movieService.getById(movieId).getTitle());
        System.out.print("Available tickets: " + ticketService.countOfAvailableTickets(movieId));
        if (ticketService.countOfAvailableTickets(movieId) != 0) {

            System.out.println('\n' + USER_MOVIE_MENU);
            step = sc.nextLine();

            switch (step) {
                case "1" -> {
                    System.out.println("buy ticket. choose seat");
                    List<Ticket> notReservedTickets = ticketService.listOfAvailableTickets(movieId);

                    System.out.format("%-4s %-10s\n", "seat", "price");
                    for (Ticket t : notReservedTickets) {
                        System.out.format("%-4s %-10s\n", t.getSeat(), t.getPrice());
                    }
                    int cinemaSeat = sc.nextInt();
                    sc.nextLine(); //иначе в меню пользователя считает пустую строку

                    for (Ticket t : notReservedTickets) {
                        if (cinemaSeat == t.getSeat()) {
                            ticketService.reserveTicket(user, cinemaSeat, movieId);                                         // User нужен???
                            break;
                        }
                    }
                }
                case "0" -> UserController.userMenu(user);
                default -> System.out.println("something goes wrong");
            }
        } else {
            System.out.println("\nnot available tickets for this film");
            UserController.userMenu(user);
        }
    }

    public static void movieMenuManager() {
        movieService.movieInfo();
        System.out.println(MANAGER_MOVIE_MENU);
        step = sc.nextLine();
        switch (step) {
            case "1" -> {
                System.out.println("buy ticket for user");
            }
            case "2" -> {
                System.out.println("return ticket for user");
            }
            case "0" -> ManagerController.managerMenu();
            default -> System.out.println("something goes wrong");
        }


    }


    public static int selectMovie() {
        System.out.println("Select film id");
        int movieId = sc.nextInt();
        sc.nextLine();// чтоб в меню не считывалась пустая строка

        Movie m = movieService.getById(movieId);
        System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().getTime()));
//        System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().toLocalDate()));   // !!! date


        return movieId;
    }


}


