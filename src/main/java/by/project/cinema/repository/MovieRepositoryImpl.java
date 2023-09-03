package by.project.cinema.repository;

import by.project.cinema.model.Movie;
import by.project.cinema.util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public boolean create(Movie movie) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO movie (title, date) VALUES (?, ?)");
            statement.setString(1, movie.getTitle());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(movie.getDate()));
            statement.execute();


            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE movie SET title = ?, date = ? WHERE id = ?");
            statement.setString(1, movie.getTitle());
            statement.setTimestamp(2, java.sql.Timestamp.valueOf(movie.getDate()));
            statement.setInt(3, movie.getId());
            statement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM movie WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Timestamp t = resultSet.getTimestamp("date");
                LocalDateTime date = t.toLocalDateTime();
                Movie m = new Movie(id, title, date);
                movieList.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public Movie getMovieById(int movieId) {
        Movie movie = null;
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie WHERE id=?");
            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Timestamp t = resultSet.getTimestamp("date");
                LocalDateTime date = t.toLocalDateTime();
                movie = new Movie(id, title, date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return movie;
    }

    @Override
    public Movie getByTitle(String movieTitle) {
        Movie movie = null;
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie WHERE title=?");
            preparedStatement.setString(1, movieTitle);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Timestamp t = resultSet.getTimestamp("date");
                LocalDateTime date = t.toLocalDateTime();
                movie = new Movie(id, title, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public boolean isExistMovie(int id) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie WHERE id=?");
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
