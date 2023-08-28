package by.project.cinema.controller;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Ticket;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static by.project.cinema.util.Constants.*;

public class ManagerController {
    public static void managerMenu() {
//        step = "?";
        while (!step.equals("0")) {
            System.out.println(MANAGER_MENU);
            step = sc.nextLine();
            switch (step) {
                case "1" -> {
                    System.out.println(ENTER_MOVIE_TITLE);
                    String title = sc.nextLine();
                    System.out.println(ENTER_MOVIE_DATE + MOVIE_DATE_FORMAT);
//                    String dateStr = "01.02.2011 15:45";
                String dateStr = sc.nextLine();  //TODO проверка ввода даты

                    Date date = stringToDate(dateStr);
                    Timestamp timestamp = new Timestamp(date.getTime());
//                    LocalDateTime timestamp = LocalDateTime.parse(dateStr);                                             // !!! date
                    Movie movie = new Movie(title, timestamp);
                    movieService.create(movie);

                    // если фильм создан, в БД заносятся 10 пустых билетов
                    for (int i = 1; i <= 10; i++) {
                        Ticket ticket = new Ticket();
                        ticket.setSeat(i);
                        ticket.setPrice(10.00);
                        ticket.setInStock(true);
                        ticket.setMovieId(movieService.getByTitle(title).getId());
                        ticketService.create(ticket);
                    }
                }                               //create film
                case "2" -> {
                    System.out.println("update film");
                }                               //update film
                case "3" -> {
                    System.out.println("delete film");
                }                               //delete film
                case "4" -> {
                    System.out.println("get film by id");
                }                               //get film by id
                case "5" -> MovieController.movieMenuManager(); //get all films
                case "0" -> MainController.mainMenu();          //back
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