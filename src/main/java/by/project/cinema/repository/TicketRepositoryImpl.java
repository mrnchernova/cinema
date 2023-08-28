package by.project.cinema.repository;

import by.project.cinema.model.Ticket;
import by.project.cinema.model.User;
import by.project.cinema.util.ConnectionDB;
import com.sun.source.tree.DefaultCaseLabelTree;
import lombok.Builder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepositoryImpl implements TicketRepository {
    @Override
    public boolean create(Ticket ticket) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO ticket (seat,price,in_stock,movie_id) VALUES (?,?,?,?)");
            statement.setInt(1, ticket.getSeat());
            statement.setDouble(2, ticket.getPrice());
            statement.setBoolean(3, ticket.isInStock());
            statement.setInt(4, ticket.getMovieId());
            statement.execute();
            System.out.println("database successfully updated");
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Ticket> getTickets() {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ticket");
            while (resultSet.next()) {
                Ticket t = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("person_id"),
                        resultSet.getInt("seat"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("in_stock"),
                        resultSet.getInt("movie_id"));

                ticketList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    @Override
    public List<Ticket> getTicketsByMovieId(int movieId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ticket WHERE movie_id=?");
            preparedStatement.setInt(1, movieId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ticket t = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("person_id"),
                        resultSet.getInt("seat"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("in_stock"),
                        resultSet.getInt("movie_id"));

                ticketList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    @Override
    public List<Ticket> getTicketsByUserId(int userId) {
        List<Ticket> ticketList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ticket WHERE person_id=?");
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ticket t = new Ticket(
                        resultSet.getInt("id"),
                        resultSet.getInt("person_id"),
                        resultSet.getInt("seat"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("in_stock"),
                        resultSet.getInt("movie_id"));

                ticketList.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    @Override
    public boolean reserveTicket(User user, int seat, int movieId) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ticket SET person_id = ?, in_stock=? WHERE seat = ? AND movie_id=?");
            statement.setInt(1, user.getId());
            statement.setBoolean(2, false);
            statement.setInt(3, seat);
            statement.setInt(4, movieId);
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean returnTicket(int id) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE ticket SET person_id=null, in_stock=? WHERE id = ?");
            statement.setBoolean(1, true);
            statement.setInt(2, id);
//            System.out.println("TICKET RETURNED");
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isExistTicket(int id) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ticket WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
