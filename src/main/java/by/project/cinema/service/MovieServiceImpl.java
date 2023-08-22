package by.project.cinema.service;

import by.project.cinema.model.Movie;
import by.project.cinema.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

import static by.project.cinema.controller.MovieController.dateFormat;
import static by.project.cinema.util.Constants.movieService;

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
    public boolean update(Movie movie) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.getMovies();
    }

    @Override
    public Optional<Movie> getById(int id) {
        return Optional.ofNullable(movieRepository.getById(id));
    }

    @Override
    public Movie getByTitle(String title) {
        return movieRepository.getByTitle(title);
    }

    @Override
    public boolean isExistMovie(int id) {
        return false;
    }

    @Override
    public void movieInfo() {
        System.out.println("list of films");
        List<Movie> movies = movieRepository.getMovies();
        for (Movie m : movies) {
            System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().getTime()));
//            System.out.format("%-4s %-35s %-15s\n", m.getId(), m.getTitle(), dateFormat.format(m.getDate().toLocalDate()));       // !!! date
        }
    }
}
