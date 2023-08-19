package by.project.cinema.repository;

import by.project.cinema.model.Movie;
import by.project.cinema.model.Role;
import by.project.cinema.model.User;
import by.project.cinema.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public boolean create(Movie movie) {
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO movie (title, date) VALUES (?, ?)");
            statement.setString(1, movie.getTitle());
            statement.setTimestamp(2, movie.getDate());                                                                 // !!! date
//            statement.setDate(2, Date.valueOf(movie.getDate().toLocalDate()));
            statement.execute();
            System.out.println("database successfully updated");

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Movie movie) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movieList = new ArrayList<>();
        try (Connection connection = ConnectionDB.open()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie");
            while (resultSet.next()) {
                Movie m = new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getTimestamp("date"));                                                                  // !!! date


                movieList.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public Movie getById(int movieId) {
        Movie movie = null;
        try (Connection connection = ConnectionDB.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM movie WHERE id=?");
            preparedStatement.setInt(1, movieId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                Timestamp date = resultSet.getTimestamp("date");
                movie = new Movie(id, title, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                Timestamp date = resultSet.getTimestamp("date");
                movie = new Movie(id, title, date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return movie;
        }

        @Override
        public boolean isExistMovie ( int id){
            return false;
        }
    }
