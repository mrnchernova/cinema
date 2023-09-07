package by.project.cinema.service;

import by.project.cinema.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean create(Movie movie);

    boolean updateMovie(Movie movie);

    boolean delete(int id);

    Optional<Movie> getMovieById(int id);

    Optional<Movie> getByTitle(String title);

    boolean isExistMovie(int id);

    void getMovies();
}
