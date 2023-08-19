package by.project.cinema.controller;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;

import java.util.List;

import static by.project.cinema.util.Constants.*;

public class TicketController {

    public static void ticketMenuUser(User user) {


        List<Ticket> tickets = ticketService.getTicketsByUserId(user.getId());
        System.out.format("%-4s %-4s %-10s %-40s\n", "id", "seat", "price", "movie");
        for (Ticket t : tickets) {
            System.out.format("%-4s %-4s %-10s", t.getId(), t.getSeat(), t.getPrice());
            System.out.println(movieService.getById(t.getMovieId()).getTitle());
        }

        System.out.println('\n' + USER_TICKET_MENU);
        step = sc.nextLine();

        switch (step) {
            case "1" -> {
                System.out.println("select id for return ticket");
                int ticketId = sc.nextInt();
                sc.nextLine();
                ticketService.returnTicket(ticketId);


            }
            case "0" -> UserController.userMenu(user);
            default -> System.out.println("something goes wrong");
        }

    }


}

