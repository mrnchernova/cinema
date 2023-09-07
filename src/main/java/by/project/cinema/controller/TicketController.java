package by.project.cinema.controller;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;
import java.util.List;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.*;

@Slf4j
public class TicketController {

    public static void orderTicket(User user, User manager) {
        movieService.getMovies();

        System.out.println(ENTER_MOVIE_ID);
        try {
            int movieId = sc.nextInt();
            sc.nextLine();

            if (movieService.getMovieById(movieId).isEmpty()) {
                System.out.format(MOVIE_ID_NOT_FOUND, movieId);
            } else {
                System.out.print(TICKET_AVAILABLE + ticketService.countOfAvailableTickets(movieId));
                if (ticketService.countOfAvailableTickets(movieId) != 0) {
                    System.out.println('\n' + USER_MOVIE_MENU);
                    step = sc.nextLine();

                    switch (step) {
                        case "1" -> {
                            log.info(MENU_BUY_TICKET);
                            boolean ticketReserved = false;
                            System.out.println(TICKET_SEAT);
                            List<Ticket> notReservedTickets = ticketService.listOfAvailableTickets(movieId);

                            System.out.format("%-4s %-10s\n", SEAT, PRICE);
                            for (Ticket t : notReservedTickets) {
                                System.out.format("%-4s %-10s\n", t.getSeat(), t.getPrice());
                            }

                            boolean validCinemaSeat = false;
                            while (!validCinemaSeat) {
                                try {
                                    int cinemaSeat = sc.nextInt();
                                    sc.nextLine();

                                    for (Ticket t : notReservedTickets) {
                                        if (cinemaSeat == t.getSeat()) {
                                            if (ticketService.reserveTicket(user, cinemaSeat, movieId)) {
                                                System.out.printf(TICKET_ORDERED_FOR_USER, user.getUsername(),
                                                        cinemaSeat, movieService.getMovieById(movieId).get().getTitle());
                                                ticketReserved = true;
                                                validCinemaSeat = true;
                                                log.info(TICKET_ORDERED_FOR_USER_LOG + user.getUsername() +
                                                        _MOVIE + movieService.getMovieById(movieId).get().getTitle() +
                                                        _SEAT + cinemaSeat);

                                            }
                                        }
                                    }
                                    if (!ticketReserved) {
                                        System.out.println(SEAT_UNKNOWN);
                                        log.error(TICKET_NOT_ORDERED);
                                        validCinemaSeat = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println(SEAT_WRONG);
                                    validCinemaSeat = false;
                                    sc.next();
                                }
                            }
                        }
                        case "0" -> {
                            if (manager == null) {
                                UserController.userMenu(user);
                            } else {
                                ManagerController.managerMenu(manager);
                            }
                        }
                        default -> System.out.println(SOMETHING_WRONG);
                    }
                } else {
                    System.out.println("\n" + TICKET_SOLD_OUT);
                    UserController.userMenu(user);
                }

            }
        } catch (InputMismatchException e) {
            System.out.println(UNKNOWN_ID);
            sc.nextLine();
        }
    }


    public static void returnTicket(User user, User manager) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(user.getId());

        if (tickets.size() != 0) {
            System.out.format("%-4s %-4s %-10s %-40s\n", ID, SEAT, PRICE, TITLE);
            for (Ticket t : tickets) {
                System.out.format("%-4s %-4s %-10s", t.getId(), t.getSeat(), t.getPrice());
                System.out.println(movieService.getMovieById(t.getMovieId()).get().getTitle());
            }
            System.out.println('\n' + USER_TICKET_MENU);
            step = sc.nextLine();

            switch (step) {
                case "1" -> {
                    log.info(MENU_RETURN_TICKET);
                    System.out.println(ENTER_ID_FOR_RETURN_TICKET);
                    boolean ticketReturned = false;
                    while (!ticketReturned) {
                        try {
                            int ticketId = sc.nextInt();
                            sc.nextLine();
                            for (Ticket ti : tickets) {
                                if (ti.getId() == ticketId) {
                                    ticketService.returnTicket(ticketId);
                                    System.out.println(TICKET_RETURNED);
                                    log.info(TICKET_RETURNED_ID + ticketId);
                                    ticketReturned = true;
                                }
                            }
                            if (!ticketReturned) {
                                System.out.println(TICKET_NOT_FOUND);
                                log.error(TICKET_NOT_FOUND_ID + ticketId);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(TICKET_UNKNOWN);
                            sc.next();
                        }
                    }
                }

                case "0" -> {
                    if (manager == null) {
                        UserController.userMenu(user);
                    } else {
                        ManagerController.managerMenu(manager);
                    }
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        } else {
            System.out.printf(TICKET_NOT_ORDERED_BY_USER, user.getUsername());
        }
    }
}

