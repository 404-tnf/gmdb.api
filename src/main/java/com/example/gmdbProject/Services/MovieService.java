package com.example.gmdbProject.Services;

import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MoviesRepository _moviesRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository) {
        this._moviesRepository = moviesRepository;
    }

    public Iterable<Movie> getMoviesSearchCriteria(String criteria, String value) {
        if ((criteria != null && !criteria.isEmpty()) && (value != null && !value.isEmpty())) {
        switch (criteria) {
            case "title":
                return this._moviesRepository.findMoviesTitle(value);
            case "actors":
                return this._moviesRepository.findMoviesActors(value);
            case "genre":
                return this._moviesRepository.findMoviesGenre(value);
            case "language":
                return this._moviesRepository.findMoviesLanguage(value);
            default:
                return this.getAllMovies();
        }
        }
        else{
            return this.getAllMovies();
        }

    }

    public Iterable<Movie> getAllMovies() {
        return _moviesRepository.findAll();
    }
}
