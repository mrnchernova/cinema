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
        movieService.movieInfo();                                                                                       //list of films

        System.out.println(ENTER_MOVIE_ID);
        try {
         int movieId = sc.nextInt();
            sc.nextLine();

            if (movieService.getMovieById(movieId).isEmpty()) {
                System.out.format("Movie id %d not found", movieId);
            } else {
                System.out.print("Available tickets: " + ticketService.countOfAvailableTickets(movieId));
                if (ticketService.countOfAvailableTickets(movieId) != 0) {

                    System.out.println('\n' + USER_MOVIE_MENU);
                    step = sc.nextLine();

                    switch (step) {
                        case "1" -> {
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
                                                System.out.println("ticket ordered for user: " + user.getUsername());
                                                System.out.println("seat: " + cinemaSeat);
                                                System.out.println("movie: " + movieService.getMovieById(movieId).get().getTitle());
                                                ticketReserved = true;
                                                validCinemaSeat = true;
                                                log.info("ticket ordered for user: " + user.getUsername() + " movie:" + movieService.getMovieById(movieId).get().getTitle() + " seat:" + cinemaSeat);
                                            }
                                        }
                                    }
                                    if (!ticketReserved) {
                                        System.out.println("Unknown seat");
                                        log.error("Ticket not ordered");
                                        validCinemaSeat = false;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong seat number");
                                    validCinemaSeat = false;
                                    sc.next();
                                }
                            }
                        }//buy ticket
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
        } catch (InputMismatchException e) {
            System.out.println("Unknown id");
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
                    System.out.println(TICKET_RETURN_BY_ID);
                    boolean ticketReturned = false;
                    while (!ticketReturned) {
                        try {
                            int ticketId = sc.nextInt();
                            sc.nextLine();
                            for (Ticket ti : tickets) {
                                if (ti.getId() == ticketId) {
                                    ticketService.returnTicket(ticketId);
                                    System.out.println("Ticket returned");
                                    log.info("Ticket returned" + " id:" + ticketId);
                                    ticketReturned = true;
                                }
                            }
                            if (!ticketReturned) {
                                System.out.println("Ticket not found");
                                log.error("Ticket not found" + " id:" + ticketId);
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Unknown ticket");
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
                }                                                                                       //back
                default -> System.out.println(SOMETHING_WRONG);
            }


        } else {
            System.out.println("User " + user.getUsername() + " didn't reserve tickets");
        }
    }

}

