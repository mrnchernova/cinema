package by.project.cinema.service;

import by.project.cinema.model.Movie;
import by.project.cinema.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

import static by.project.cinema.util.Constants.*;
import static by.project.cinema.util.Util.formatter;

public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public boolean create(Movie movie) {
        return movieRepository.create(movie);
    }

    @Override
    public boolean updateMovie(Movie movie) {
        return movieRepository.updateMovie(movie);
    }

    @Override
    public boolean delete(int id) {
        return movieRepository.delete(id);
    }

    @Override
    public Optional<Movie> getMovieById(int id) {
        return Optional.ofNullable(movieRepository.getMovieById(id));
    }

    @Override
    public Optional<Movie> getByTitle(String title) {
        return Optional.ofNullable(movieRepository.getByTitle(title));
    }

    @Override
    public boolean isExistMovie(int id) {
        return movieRepository.isExistMovie(id);
    }

    @Override
    public void getMovies() {
        List<Movie> movies = movieRepository.getMovies();
        System.out.printf("%-4s %-35s %-15s\n", ID, TITLE, DATE);
        for (Movie m : movies) {
            System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), formatter.format(m.getDate()));
        }
    }
}
