package by.project.cinema.util;


import java.util.Scanner;

public class Constants {

    public static Scanner sc = new Scanner(System.in);

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String ROLE = "role";





    public static final String MAIN_MENU = """
            1. list of films
            2. registration
            3. sign in
            4. admin menu
            0. exit
            """;


    /**todo после списка фильмов разрешить выбрать нужный фильм
     *      купить на него билет
     *
     *      your tickets позволяет просмотреть купленные билеты
     *                  удалить билет
     */

    public static final String USER_MENU = """
            1. list of films
            2. your tickets            
            3. change password
            0. back
            """;


    public static final String ADMIN_MENU = """
            1. create user
            2. update user -            
            3. delete user
            4. get user by id
            5. get all users
            0. back
            """;



}
