package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
                    String title = "";
                    while (title.isEmpty()) {
                        System.out.println(ENTER_MOVIE_TITLE);
                        title = sc.nextLine();
                    }

                    System.out.println(ENTER_MOVIE_DATE + MOVIE_DATE_FORMAT);
                    String dateStr = sc.nextLine();

                    try {
                        LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                        Movie movie = new Movie(title, date);
                        if (movieService.create(movie)) {
                            System.out.println("Movie successfully created");
                        } else {
                            System.out.println(SOMETHING_WRONG);
                        }

                        for (int i = 1; i <= 10; i++) {                                                                     // if movie is created, 10 empty tickets are entered into DB
                            Ticket ticket = new Ticket();
                            ticket.setSeat(i);
                            ticket.setPrice(10.00);
                            ticket.setInStock(true);
                            ticket.setMovieId(movieService.getByTitle(title).getId());
                            ticketService.create(ticket);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format. Movie can't be created");
                    }


                }                                                                                       //create film
                case "2" -> {
                    System.out.println("enter movie id for update");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (movieService.isExistMovie(id)) {
                        Movie movie = movieService.getMovieById(id).get();

                        System.out.println("new movie title. Previous title: " + movie.getTitle());
                        String title = sc.nextLine();
                        if (!title.isEmpty()) {
                            movie.setTitle(title);
                        }

                        System.out.println("new movie date. " + MOVIE_DATE_FORMAT + " Previous date: " + formatter.format(movie.getDate()));
                        String dateStr = sc.nextLine();

                        if (!dateStr.isEmpty()) {
                            try {
                                LocalDateTime date = LocalDateTime.parse(dateStr, formatter);
                                movie.setDate(date);
                            } catch (Exception e) {
                                System.out.println("Wrong date format. Date can't be changed");
                            }
                        }

                        if (movieService.updateMovie(movie)) {
                            System.out.println("movie updated");
                        } else {
                            System.out.println("movie not updated");
                        }
                    } else {
                        System.out.println("movie not found");
                    }
                }                                                                                       //update film
                case "3" -> {
                    System.out.println("Enter movie id for delete");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if (movieService.isExistMovie(id)) {
                        if (movieService.delete(id)) {
                            System.out.println("movie deleted");
                        }
                    } else {
                        System.out.println("movie not found");
                    }
                }                                                                                       //delete film
                case "4" -> MovieController.movieMenuManager(user);                                                     //get all films
                case "0" -> MainController.mainMenu();                                                                  //back
                default -> System.out.println(SOMETHING_WRONG);
            }
        }
    }
}