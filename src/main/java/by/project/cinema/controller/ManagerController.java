package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static by.project.cinema.util.Constants.*;

public class ManagerController {
    public static void managerMenu(User user) {
        step = DEFAULT;
        while (!step.equals("0")) {
            System.out.println(MANAGER_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println(ENTER_MOVIE_TITLE);
                    String title = sc.nextLine();
                    System.out.println(ENTER_MOVIE_DATE + MOVIE_DATE_FORMAT);
                                                                                                                        //String dateStr = "01.02.2011 15:45";
                    String dateStr = sc.nextLine();                                                                     //TODO проверка ввода даты

                    try {
                        Date date = stringToDate(dateStr);
                        Timestamp timestamp = new Timestamp(date.getTime());
//                    LocalDateTime timestamp = LocalDateTime.parse(dateStr);                                           // !!! date
                        Movie movie = new Movie(title, timestamp);
                        if (movieService.create(movie)) {
                            System.out.println("database successfully updated");
                        } else {
                            System.out.println(SOMETHING_WRONG);
                        }

                    } catch (Exception e) {
                        System.out.println("Unparseable date. Movie can't be created");
                        ManagerController.managerMenu(user);
                    }


                    for (int i = 1; i <= 10; i++) {                                                                     // if movie is created, 10 empty tickets are entered into DB
                        Ticket ticket = new Ticket();
                        ticket.setSeat(i);
                        ticket.setPrice(10.00);
                        ticket.setInStock(true);
                        ticket.setMovieId(movieService.getByTitle(title).getId());
                        ticketService.create(ticket);
                    }
                }                                                                                       //create film
                case "2" -> {
                    System.out.println("update film");
                    System.out.println("enter film id");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (movieService.isExistMovie(id)) {
                        Movie movie = movieService.getMovieById(id).get();

                        System.out.println("new movie title");
                        String title = sc.nextLine();
                        if (!title.isEmpty()) {
                            movie.setTitle(title);
                        }

                        System.out.println("new movie date. " + MOVIE_DATE_FORMAT);
                        String dateStr = sc.nextLine();

                        if (!dateStr.isEmpty()) {
                            try {
                                Date date = stringToDate(dateStr);
                                Timestamp timestamp = new Timestamp(date.getTime());
                                movie.setDate(timestamp);

                            } catch (Exception e) {
                                System.out.println("Unparseable date. Date can't be changed");
                            }

                        }

                        if (movieService.updateMovie(movie)) {
                            System.out.println("movie updated");
                        } else {
                            System.out.println("movie not updated");
                        }


                    }
                }                                                                                       //update film
                case "3" -> {
                    System.out.println("delete film");
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

    public static Date stringToDate(String dateStr) {
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date dateTime = new java.sql.Date(date.getTime());
        return dateTime;
    }
}