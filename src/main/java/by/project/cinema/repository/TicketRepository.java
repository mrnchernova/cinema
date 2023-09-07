package by.project.cinema.repository;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;

import java.util.List;

public interface TicketRepository {

    boolean create(Ticket ticket);

    boolean updateTicket(Ticket ticket);

    List<Ticket> getTickets();

    List<Ticket> getTicketsByMovieId(int movieId);

    List<Ticket> getTicketsByUserId(int userId);

    boolean reserveTicket(User user, int seat, int movieId);

    boolean returnTicket(int id);

    boolean isExistTicket(int id);
}
