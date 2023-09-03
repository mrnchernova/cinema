package by.project.cinema.util;

import by.project.cinema.repository.MovieRepositoryImpl;
import by.project.cinema.repository.TicketRepositoryImpl;
import by.project.cinema.repository.UserRepositoryImpl;
import by.project.cinema.service.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Util {

    public static Scanner sc = new Scanner(System.in);
    public static String step = "-1";

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    public static MovieService movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    public static TicketService ticketService = new TicketServiceImpl(new TicketRepositoryImpl());

}
