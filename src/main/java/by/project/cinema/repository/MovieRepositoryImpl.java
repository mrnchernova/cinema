package by.project.cinema.repository;

import by.project.cinema.model.Movie;
import by.project.cinema.util.ConnectionDB;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static by.project.cinema.util.Constants.*;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public boolean create(Movie movie) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_INTO_MOVIE_TITLE_DATE);
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
            PreparedStatement statement = connection.prepareStatement(UPDATE_MOVIE_SET_TITLE_DATE_BY_ID);
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
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MOVIE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_FROM_MOVIE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_MOVIE_BY_ID);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_MOVIE_BY_TITLE);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_MOVIE_BY_ID);
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
