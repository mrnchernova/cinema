package by.project.cinema.util;


import by.project.cinema.repository.MovieRepositoryImpl;
import by.project.cinema.repository.TicketRepositoryImpl;
import by.project.cinema.repository.UserRepositoryImpl;
import by.project.cinema.service.*;

import java.util.Scanner;

public class Constants {

    public static Scanner sc = new Scanner(System.in);
    public static String step = "-1";
    public static UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    public static MovieService movieService = new MovieServiceImpl(new MovieRepositoryImpl());
    public static TicketService ticketService = new TicketServiceImpl(new TicketRepositoryImpl());

    public static final String DEFAULT = "default value";
    public static final String WELCOME = "Welcome, ";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String SEAT = "seat";
    public static final String PRICE = "price";
    public static final String MOVIE = "movie";

    public static final String ENTER_USER_ID = "Enter user id: ";
    public static final String ENTER_USERNAME = "Enter username: ";
    public static final String ENTER_PASSWORD = "Enter password: ";
    public static final String ENTER_EMAIL = "Enter email: ";
    public static final String ENTER_MOVIE_ID = "Enter film id: ";
    public static final String ENTER_MOVIE_TITLE = "Enter title: ";
    public static final String ENTER_MOVIE_DATE = "Enter date ";
    public static final String MOVIE_DATE_FORMAT = "xx.xx.xxxx xx:xx ";
    public static final String TICKET_RETURN_BY_ID = "Select id for return ticket ";

    /** DB requests */
    public static final String SMTH = "smth";

    /** information messages */
    public static final String PASSWORD_RULE = "The password must consist of a capital letter, a lowercase letter, a number, and a symbol. Password length [6..20]. ";
    public static final String PASSWORD_NOT_VALID = "Password not correct. ";
    public static final String EMAIL_NOT_VALID = "Email not correct. ";

    public static final String TRY_AGAIN = "Try again ";
    public static final String NOT_SUCCESSFUL = "Not Successful. ";
    public static final String SUCCESSFUL = "Successful. ";
    public static final String SOMETHING_WRONG = "Something goes wrong";

    public static final String USER_EXISTS = "Such user already exists. ";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_UPDATED = "User successfully updated";
    public static final String USER_DELETED = "User successfully deleted";
    public static final String USER_NEW_USERNAME = "Write new username or press Enter";
    public static final String USER_NEW_PASSWORD = "Write new password or press Enter";
    public static final String USER_NEW_EMAIL = "Write new email or press Enter";
    public static final String USER_NEW_ROLE = "Choose new role \n1 Admin \n2 User \n3 Manager";


    public static final String UNKNOWN_ROLE = "Undefined role";
    public static final String NEEDS_REGISTRATION = "You need registered first";

    public static final String TICKET_SEAT = "Choose seat ";
    public static final String TICKET_SOLD_OUT = "Not available tickets for this film";

    public static final String TICKET_BUY_FOR_USER = "Buy ticket for user";
    public static final String TICKET_RETURN_FOR_USER = "Return ticket for user";






    public static final String MAIN_MENU = """
            
            -- MAIN MENU --
            1. list of films
            2. registration
            3. sign in
            0. exit
            """;

    public static final String USER_MENU = """
            
            -- USER MENU --
            1. list of films
            2. your tickets            
            3. update account
            0. back
            """;

    public static final String UPDATE_ACCOUNT = """
            
            -- UPDATE ACCOUNT --
            1. change password
            2. change email         
            0. back
            """;

    public static final String USER_MOVIE_MENU = """
            
            --MOVIE MENU--
            1. buy ticket
            0. back
            """;

    public static final String USER_TICKET_MENU = """

            --TICKET MENU--
            1. return ticket
            0. back
            """;

    public static final String ADMIN_MENU = """
            
            -- ADMIN MENU --
            1. create user
            2. update user          
            3. delete user
            4. get user by id
            5. get all users
            0. back
            """;

    public static final String MANAGER_MENU = """
            
            -- MANAGER MENU --
            1. create film
            2. update film      
            3. delete film
            4. get all films
            0. back
            """;

    public static final String MANAGER_MOVIE_MENU = """
            
            --MOVIE MENU--
            1. buy ticket for user
            2. return ticket for user
            0. back
            """;



}
