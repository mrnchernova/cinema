package by.project.cinema.service;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;
import by.project.cinema.repository.TicketRepository;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean create(Ticket ticket) {
        return ticketRepository.create(ticket);
    }

    @Override
    public boolean updateTicket(Ticket ticket) {
        return ticketRepository.updateTicket(ticket);
    }

    @Override
    public List<Ticket> getTicketsByMovieId(int movieId) {
        return ticketRepository.getTicketsByMovieId(movieId);
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketRepository.getTicketsByUserId(userId);
    }

    @Override
    public boolean reserveTicket(User user, int seat, int movieId) {
        return ticketRepository.reserveTicket(user, seat, movieId);
    }

    @Override
    public boolean returnTicket(int id) {
        return ticketRepository.returnTicket(id);
    }

    @Override
    public int countOfAvailableTickets(int movieId) {
        List<Ticket> tickets = ticketRepository.getTicketsByMovieId(movieId);
        int count = 0;
        for (Ticket ticket : tickets) {
            if (ticket.isInStock()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Ticket> listOfAvailableTickets(int movieId) {
        List<Ticket> tickets = ticketRepository.getTicketsByMovieId(movieId);
        List<Ticket> notReservedTickets = new ArrayList<>();
        for (Ticket ticket : tickets) {
            if (ticket.isInStock()) {
                notReservedTickets.add(ticket);
            }
        }
        return notReservedTickets;
    }
}
