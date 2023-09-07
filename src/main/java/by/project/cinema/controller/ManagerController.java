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
                    log.info("MENU: Create Movie");
                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println(ENTER_MOVIE_TITLE);
                        title = sc.nextLine();
                    }


                    System.out.println("Ticket costs:");
                    double price = 0;
                    boolean validPrice = false;
                    while (!validPrice) {
                        try {
                            price = sc.nextDouble();
                            sc.nextLine();
                            validPrice = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Enter valid price");
                            sc.nextLine();
                        }
                    }

                    System.out.println(ENTER_MOVIE_DATE + MOVIE_DATE_FORMAT);
                    String dateStr = sc.nextLine();
                    try {
                        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                        Movie movie = new Movie(title, date);
                        if (movieService.create(movie)) {
                            System.out.println("Movie successfully created");
                            log.info("Movie successfully created. Title: \"" + title + "\" | date:" + dateStr);
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
                        System.out.println("Created 10 tickets for movie: \"" + movie.getTitle() + "\" | price:" + price);
                        log.info("Created 10 tickets for movie: \"" + movie.getTitle() + "\" | price:" + price);

                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format. Movie can't be created");
                        log.error("Wrong date format. Movie can't be created");
                    }


                }
                case "2" -> {
                    log.info("MENU: Update Movie");
                    System.out.println("Enter movie id for update");
                    try {
                        int id = sc.nextInt();
                        sc.nextLine();
                        if (movieService.isExistMovie(id)) {
                            Movie movie = movieService.getMovieById(id).orElse(null);

                            newTitle(movie);
                            newPrice(movie);
                            newDate(movie);

                            if (movieService.updateMovie(movie)) {
                                System.out.println("Movie updated");
                                log.info("Movie updated. New title: \"" + movie.getTitle() + "\" | new date:" + formatter.format(movie.getDate()));
                            } else {
                                System.out.println("Movie not updated");
                                log.error("Movie not updated");
                            }
                        } else {
                            System.out.println("Movie not found");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Unknown movie id");
                        sc.next();
                    }
                }
                case "3" -> {
                    log.info("MENU: Delete Movie");
                    System.out.println("Enter movie id for delete");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (movieService.isExistMovie(id)) {
                        if (movieService.delete(id)) {
                            System.out.println("Movie deleted");
                            log.info("Movie deleted. id:" + id);
                        }
                    } else {
                        System.out.println("Movie not found");
                        log.info("Movie not found. id:" + id);
                    }
                }
                case "4" -> {
                    log.info("MENU: List Of Movies");
                    MovieController.movieMenuManager(user);
                }
                case "0" -> {
                    log.info("Log out");
                    MainController.mainMenu();
                }
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }

    private static void newPrice(Movie movie) {
        List<Ticket> tickets = ticketService.getTicketsByMovieId(movie.getId());
        System.out.println("New ticket price. Previous price: " + tickets.get(0).getPrice());
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
                        System.out.println("Price cannot be negative");    
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Enter valid price");
                    sc.nextLine();
                }
            }

            for (Ticket t : tickets) {
                t.setPrice(price);
                ticketService.updateTicket(t);
            }
        } else {
            System.out.println("You cannot change price. Some tickets was sold out");
        }
    }

    private static void newTitle(Movie movie) {
        System.out.println("New movie title. Previous title: \"" + movie.getTitle() + "\"");
        String title = sc.nextLine();
        if (!title.isEmpty()) {
            movie.setTitle(title);
        }
    }

    private static void newDate(Movie movie) {
        System.out.println("Mew movie date. " + MOVIE_DATE_FORMAT + " Previous date: " + formatter.format(movie.getDate()));
        String dateStr = sc.nextLine();
        if (!dateStr.isEmpty()) {
            try {
                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                movie.setDate(date);
            } catch (Exception e) {
                System.out.println("Wrong date format. Date can't be changed");
            }
        }
    }
}