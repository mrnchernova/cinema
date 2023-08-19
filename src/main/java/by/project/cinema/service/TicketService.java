package by.project.cinema.service;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;

import java.util.List;

public interface TicketService {

    boolean create(Ticket ticket);

    List<Ticket> getTickets();

    List<Ticket> getTicketsByMovieId(int movieId);
    List<Ticket> getTicketsByUserId(int userId);

    boolean reserveTicket(User user, int seat, int movieId);
    boolean returnTicket(int id);

    int countOfAvailableTickets(int movieId);

    List<Ticket> listOfAvailableTickets(int movieId);

}
