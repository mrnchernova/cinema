package by.project.cinema.util;

public class Constants {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";
    public static final String SEAT = "seat";
    public static final String PRICE = "price";
    public static final String TITLE = "title";
    public static final String DATE = "date";

    public static final String ENTER_USER_ID = "Enter user id: ";
    public static final String ENTER_USERNAME = "Enter username: ";
    public static final String ENTER_PASSWORD = "Enter password: ";
    public static final String ENTER_EMAIL = "Enter email: ";
    public static final String ENTER_MOVIE_ID = "Enter movie id: ";
    public static final String ENTER_MOVIE_TITLE = "Enter title: ";
    public static final String ENTER_MOVIE_DATE = "Enter date ";
    public static final String ENTER_ID_FOR_RETURN_TICKET = "Enter id for return ticket: ";
    public static final String ENTER_VALID_PRICE = "Enter valid price";


    /* DB connection */
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_DRIVER_NOT_FOUND = "Driver mysql can't be find";
    public static final String DB_ERROR_CONNECTION = "Error connecting to database";


    /* DB requests */
    public static final String SELECT_ALL_FROM_PERSON = "SELECT * FROM person";
    public static final String SELECT_ALL_FROM_PERSON_BY_ID = "SELECT * FROM person WHERE id = ?";
    public static final String SELECT_ALL_FROM_PERSON_BY_USERNAME = "SELECT * FROM person WHERE username = ?";
    public static final String SELECT_ALL_FROM_PERSON_BY_USERNAME_AND_PASSWORD = "SELECT * FROM person WHERE username = ? AND password = ?";
    public static final String INSERT_INTO_PERSON_USERNAME_PASSWORD_EMAIL_ROLE = "INSERT INTO person (username, password, email, role) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_PERSON_SET_USERNAME_PASSWORD_EMAIL_ROLE = "UPDATE person SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
    public static final String DELETE_PERSON = "DELETE FROM person WHERE id = ?";

    public static final String SELECT_ALL_FROM_MOVIE = "SELECT * FROM movie";
    public static final String SELECT_ALL_FROM_MOVIE_BY_ID = "SELECT * FROM movie WHERE id = ?";
    public static final String SELECT_ALL_FROM_MOVIE_BY_TITLE = "SELECT * FROM movie WHERE title = ?";
    public static final String INSERT_INTO_MOVIE_TITLE_DATE = "INSERT INTO movie (title, date) VALUES (?, ?)";
    public static final String UPDATE_MOVIE_SET_TITLE_DATE_BY_ID = "UPDATE movie SET title = ?, date = ? WHERE id = ?";
    public static final String DELETE_MOVIE = "DELETE FROM movie WHERE id = ?";

    public static final String INSERT_INTO_TICKET_SEAT_PRICE_INSTOCK_MOVIEID = "INSERT INTO ticket (seat,price,in_stock,movie_id) VALUES (?,?,?,?)";
    public static final String UPDATE_TICKET_SET_PRICE_BY_MOVIEID = "UPDATE ticket SET price = ? WHERE movie_id = ?";
    public static final String SELECT_ALL_FROM_TICKET = "SELECT * FROM ticket";
    public static final String SELECT_ALL_FROM_TICKET_BY_MOVIEID = "SELECT * FROM ticket WHERE movie_id = ?";
    public static final String SELECT_ALL_FROM_TICKET_BY_ID = "SELECT * FROM ticket WHERE id = ?";
    public static final String SELECT_ALL_FROM_TICKET_BY_PERSONID = "SELECT * FROM ticket WHERE person_id = ?";
    public static final String UPDATE_TICKET_SET_PERSONID_INSTOCK_BY_SEAT_AND_MOVIEID = "UPDATE ticket SET person_id = ?, in_stock = ? WHERE seat = ? AND movie_id = ?";
    public static final String UPDATE_TICKET_SET_PERSONID_NULL_INSTOCK_BY_ID = "UPDATE ticket SET person_id = null, in_stock=? WHERE id = ?";


    /* information messages */
    public static final String LOG_OUT = "Log out";
    public static final String EXIT = "Process finished with exit code 0";
    public static final String UNKNOWN_ID = "Unknown id";
    public static final String DEFAULT = "default value";
    public static final String WELCOME = "Welcome, ";


    public static final String PASSWORD_RULE = "The password must consist of a capital letter, a lowercase letter, a number, and a symbol. Password length [6..20]. ";
    public static final String PASSWORD_NOT_VALID = "Password not correct. ";
    public static final String EMAIL_NOT_VALID = "Email not correct. ";

    public static final String TRY_AGAIN = "Try again ";
    public static final String NOT_SUCCESSFUL = "Not Successful ";
    public static final String SUCCESSFUL = "Successful ";
    public static final String SOMETHING_WRONG = "Something goes wrong";

    public static final String USER_EXISTS = "Such user already exists. ";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_NOT_FOUND_TRY_AGAIN = "User not found. Try again";
    public static final String USER_UPDATED = "User successfully updated";
    public static final String USER_NOT_CREATED = "User not created";
    public static final String USER_CREATED = "User created";
    public static final String USER_CREATED_LOG = "User created: ";
    public static final String USER_DELETED = "User successfully deleted";
    public static final String USER_NEW_PASSWORD_OR_SKIP = "Write new password or press Enter";
    public static final String USER_NEW_EMAIL_OR_SKIP = "Write new email or press Enter";
    public static final String USER_NEW_ROLE = "Choose new role \n1 Admin \n2 User \n3 Manager";
    public static final String USER_CHANGED_PASSWORD = "User changed password successfully";
    public static final String USER_CHANGED_EMAIL = "User changed email successfully";
    public static final String USER_NOT_CHANGED_PASSWORD = "User couldn't change password. Password not valid";
    public static final String USER_NOT_CHANGED_EMAIL = "User couldn't change email. Email not valid";
    public static final String _EMAIL = " | email:";
    public static final String _USERNAME = " username:";
    public static final String USER_LOGGED_IN = "User logged in \t";
    public static final String USER_INCORRECT_USERNAME_OR_PASSWORD = "Incorrect username or password";
    public static final String UNKNOWN_ROLE = "Undefined role";

    public static final String _MOVIE = " | movie:";
    public static final String _DATE = " | date:";
    public static final String MOVIE_DATE_FORMAT = "xx.xx.xxxx xx:xx ";
    public static final String MOVIE_DATE_WRONG_FORMAT = "Wrong date format. Date can't be changed";
    public static final String MOVIE_NOT_CREATED = "Wrong date format. Movie can't be created";
    public static final String MOVIE_ID_NOT_FOUND = "Movie id %d not found";
    public static final String MOVIE_NOT_FOUND = "Movie not found";
    public static final String MOVIE_NOT_FOUND_ID = "Movie not found. id:";
    public static final String MOVIE_ID_UNKNOWN = "Unknown movie id";
    public static final String MOVIE_CREATED = "Movie successfully created";
    public static final String MOVIE_CREATED_LOG = "Movie successfully created: ";
    public static final String MOVIE_ID_FOR_UPDATE = "Enter movie id for update";
    public static final String MOVIE_UPDATED = "Movie updated\t";
    public static final String MOVIE_NOT_UPDATED = "Movie not updated";
    public static final String MOVIE_DELETED = "Movie deleted";
    public static final String MOVIE_DELETED_ID = "Movie deleted. id:";
    public static final String MOVIE_NEW_TITLE = "New movie title.\t";
    public static final String MOVIE_NEW_DATE = "New movie date.\t";
    public static final String MOVIE_PREVIOUS_TITLE = "Previous title: ";
    public static final String MOVIE_PREVIOUS_DATE = "Previous date: ";

    public static final String TICKET_SEAT = "Choose seat ";
    public static final String TICKET_SOLD_OUT = "Not available tickets for this film";
    public static final String TICKET_BUY_FOR_USER = "Buy ticket for user";
    public static final String TICKET_RETURN_FOR_USER = "Return ticket for user";
    public static final String TICKET_AVAILABLE = "Available tickets: ";
    public static final String TICKET_ORDERED_FOR_USER = "Ticket ordered for user: %s \nseat: %d \nmovie: %s";
    public static final String TICKET_ORDERED_FOR_USER_LOG = "Ticket ordered for user:";
    public static final String TICKET_NOT_ORDERED = "Ticket not ordered";
    public static final String TICKET_RETURNED = "Ticket returned";
    public static final String TICKET_RETURNED_ID = "Ticket returned id:";
    public static final String TICKET_UNKNOWN = "Unknown ticket";
    public static final String TICKET_NOT_FOUND = "Ticket not found";
    public static final String TICKET_NOT_FOUND_ID = "Ticket not found id:";
    public static final String TICKET_NOT_ORDERED_BY_USER = "User %s didn't reserve tickets";
    public static final String TICKET_COSTS = "Ticket costs:";
    public static final String TICKET_NEW_PRICE = "New price for tickets. Previous price: ";
    public static final String TICKET_CREATE = "Created 10 tickets for movie: ";

    public static final String _PRICE = " | price:";
    public static final String PRICE_NEGATIVE = "Price cannot be negative";
    public static final String PRICE_CANNOT_BE_CHANGED = "You cannot change price. Some tickets was sold out";

    public static final String _SEAT = " | seat:";
    public static final String SEAT_UNKNOWN = "Unknown seat";
    public static final String SEAT_WRONG = "Wrong seat number";

    public static final String NEW_TITLE = "New title:";
    public static final String _NEW_DATE = " | new date:";

    /* menu */
    public static final String MENU_BUY_TICKET = "MENU: Buy Ticket";
    public static final String MENU_RETURN_TICKET = "MENU: Return Ticket";
    public static final String MENU_CREATE_MOVIE = "MENU: Create Movie";
    public static final String MENU_UPDATE_MOVIE = "MENU: Update Movie";
    public static final String MENU_LIST_OF_MOVIES = "MENU: List Of Movies";
    public static final String MENU_DELETE_MOVIE = "MENU: Delete Movie";
    public static final String MENU_BUY_TICKET_FOR_USER = "MENU: Buy Ticket For User";
    public static final String MENU_RETURN_TICKET_FOR_USER = "MENU: Return Ticket For User";
    public static final String MENU_ORDER_TICKET = "MENU: Order Ticket";
    public static final String MENU_USER_TICKET = "MENU: User Ticket";
    public static final String MENU_UPDATE_ACCOUNT = "MENU: Update Account";
    public static final String MENU_MAIN = "MENU: Main";
    public static final String MENU_REGISTRATION = "MENU: Registration";
    public static final String MENU_LOG_IN = "MENU: Log In";

    public static final String MAIN_MENU = ANSI_YELLOW + """
                        
            -- MAIN MENU --
            1. list of films
            2. registration
            3. log in
            0. exit
            """ + ANSI_RESET;

    public static final String USER_MENU = ANSI_YELLOW + """
                        
            -- USER MENU --
            1. order ticket
            2. your tickets            
            3. update account
            0. log out
            """ + ANSI_RESET;

    public static final String UPDATE_ACCOUNT = ANSI_YELLOW + """
                        
            -- UPDATE ACCOUNT --
            1. change password
            2. change email         
            0. back
            """ + ANSI_RESET;

    public static final String USER_MOVIE_MENU = ANSI_YELLOW + """
                        
            --MOVIE MENU--
            1. buy ticket
            0. back
            """ + ANSI_RESET;

    public static final String USER_TICKET_MENU = ANSI_YELLOW + """

            --TICKET MENU--
            1. return ticket
            0. back
            """ + ANSI_RESET;

    public static final String ADMIN_MENU = ANSI_YELLOW + """
                        
            -- ADMIN MENU --
            1. create user
            2. update user         
            3. delete user
            4. get all users
            0. log out
            """ + ANSI_RESET;

    public static final String MANAGER_MENU = ANSI_YELLOW + """
                        
            -- MANAGER MENU --
            1. create film
            2. update film      
            3. delete film
            4. get all films
            0. log out
            """ + ANSI_RESET;

    public static final String MANAGER_MOVIE_MENU = ANSI_YELLOW + """
                        
            --MOVIE MENU--
            1. buy ticket for user
            2. return ticket for user
            0. back
            """ + ANSI_RESET;
}
