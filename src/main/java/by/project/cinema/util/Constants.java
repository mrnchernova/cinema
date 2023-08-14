package by.project.cinema.util;


import by.project.cinema.repository.UserRepositoryImpl;
import by.project.cinema.service.UserService;
import by.project.cinema.service.UserServiceImpl;

import java.util.Scanner;

public class Constants {

    public static Scanner sc = new Scanner(System.in);
    public static String step = "-1";
    public static UserService userService = new UserServiceImpl(new UserRepositoryImpl());


    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";




// secret command "admin"
    public static final String MAIN_MENU = """
            -- MAIN MENU --
            1. list of films
            2. registration
            3. sign in
            0. exit
            """;


    /**todo после списка фильмов разрешить выбрать нужный фильм
     *      купить на него билет
     *
     *      your tickets позволяет просмотреть купленные билеты
     *                  удалить билет
     */

    public static final String USER_MENU = """
            -- USER MENU --
            1. list of films
            2. your tickets            
            3. change password
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
            4. get film by id
            5. get all films
            0. back
            """;



}
