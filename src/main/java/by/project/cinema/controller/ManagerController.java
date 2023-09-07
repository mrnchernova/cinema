package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class ManagerController {
    public static void managerMenu(User user) {

        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println(MANAGER_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    log.info(MENU_CREATE_MOVIE);
                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println(ENTER_MOVIE_TITLE);
                        title = sc.nextLine();
                    }


                    System.out.println(TICKET_COSTS);
                    double price = 0;
                    boolean validPrice = false;
                    while (!validPrice) {
                        try {
                            price = sc.nextDouble();
                            sc.nextLine();
                            validPrice = true;
                        } catch (InputMismatchException e) {
                            System.out.println(ENTER_VALID_PRICE);
                            sc.nextLine();
                        }
                    }

                    System.out.println(ENTER_MOVIE_DATE + MOVIE_DATE_FORMAT);
                    String dateStr = sc.nextLine();
                    try {
                        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                        Movie movie = new Movie(title, date);
                        if (movieService.create(movie)) {
                            System.out.println(MOVIE_CREATED);
                            log.info(MOVIE_CREATED_LOG + title + _DATE + dateStr);
                        } else {
                            System.out.println(SOMETHING_WRONG);
                        }


                        Movie currentMovie = movieService.getByTitle(title).orElse(null);
                        assert currentMovie != null;
                        for (int i = 1; i <= 10; i++) {                                                                     // if movie is created, 10 empty tickets are entered into DB
                            Ticket ticket = new Ticket();
                            ticket.setSeat(i);
                            ticket.setPrice(price);
                            ticket.setInStock(true);
                            ticket.setMovieId(currentMovie.getId());
                            ticketService.create(ticket);
                        }
                        System.out.println(TICKET_CREATE + movie.getTitle() + _PRICE + price);
                        log.info(TICKET_CREATE + movie.getTitle() + _PRICE + price);

                    } catch (DateTimeParseException e) {
                        System.out.println(MOVIE_NOT_CREATED);
                        log.error(MOVIE_NOT_CREATED);
                    }


                }
                case "2" -> {
                    log.info(MENU_UPDATE_MOVIE);
                    System.out.println(MOVIE_ID_FOR_UPDATE);
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (movieService.isExistMovie(id)) {
                            Movie movie = movieService.getMovieById(id).orElse(null);

                            newTitle(movie);
                            newPrice(movie);
                            newDate(movie);

                            if (movieService.updateMovie(movie)) {
                                System.out.println(MOVIE_UPDATED);
                                log.info(MOVIE_UPDATED + NEW_TITLE + movie.getTitle() + _NEW_DATE + formatter.format(movie.getDate()));
                            } else {
                                System.out.println(MOVIE_NOT_UPDATED);
                                log.error(MOVIE_NOT_UPDATED);
                            }
                        } else {
                            System.out.println(MOVIE_NOT_FOUND);
                        }
                    } catch (InputMismatchException e) {
                        System.out.println(MOVIE_ID_UNKNOWN);
                        sc.next();
                    }
                }
                case "3" -> {
                    log.info(MENU_DELETE_MOVIE);
                    System.out.println(ENTER_MOVIE_ID);
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (movieService.isExistMovie(id)) {
                        if (movieService.delete(id)) {
                            System.out.println(MOVIE_DELETED);
                            log.info(MOVIE_DELETED_ID + id);
                        }
                    } else {
                        System.out.println(MOVIE_NOT_FOUND);
                        log.info(MOVIE_NOT_FOUND_ID + id);
                    }
                }
                case "4" -> {
                    log.info(MENU_LIST_OF_MOVIES);
                    MovieController.movieMenuManager(user);
                }
                case "0" -> {
                    log.info(LOG_OUT);
                    MainController.mainMenu();
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

    private static void newPrice(Movie movie) {
        List<Ticket> tickets = ticketService.getTicketsByMovieId(movie.getId());
        System.out.println(TICKET_NEW_PRICE + tickets.get(0).getPrice());
        if (ticketService.countOfAvailableTickets(movie.getId()) == 10) {

            double price = tickets.get(0).getPrice();
            boolean validPrice = false;
            while (!validPrice) {
                try {
                    price = sc.nextDouble();
                    if (price > 0) {
                        sc.nextLine();
                        validPrice = true;
                    }else {
                        System.out.println(PRICE_NEGATIVE);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(ENTER_VALID_PRICE);
                    sc.nextLine();
                }
            }

            for (Ticket t : tickets) {
                t.setPrice(price);
                ticketService.updateTicket(t);
            }
        } else {
            System.out.println(PRICE_CANNOT_BE_CHANGED);
        }
    }

    private static void newTitle(Movie movie) {
        System.out.println(MOVIE_NEW_TITLE + MOVIE_PREVIOUS_TITLE + movie.getTitle() + "");
        String title = sc.nextLine();
        if (!title.isEmpty()) {
            movie.setTitle(title);
        }
    }

    private static void newDate(Movie movie) {
        System.out.println(MOVIE_NEW_DATE + MOVIE_DATE_FORMAT + MOVIE_PREVIOUS_DATE + formatter.format(movie.getDate()));
        String dateStr = sc.nextLine();
        if (!dateStr.isEmpty()) {
            try {
                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                movie.setDate(date);
            } catch (Exception e) {
                System.out.println(MOVIE_DATE_WRONG_FORMAT);
            }
        }
    }
}