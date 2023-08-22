package by.project.cinema.service;

import by.project.cinema.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    boolean create(Movie movie);

    boolean update(Movie movie);

    boolean delete(int id);

    List<Movie> getMovies();

    Optional<Movie> getById(int id);

    Movie getByTitle(String title);

    boolean isExistMovie(int id);

    void movieInfo();
}
