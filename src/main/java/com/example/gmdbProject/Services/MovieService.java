package com.example.gmdbProject.Services;

import com.example.gmdbProject.Models.Movies;
import com.example.gmdbProject.Repository.MoviesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MoviesRepository _moviesRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository) {
        this._moviesRepository = moviesRepository;
    }

    public List<Movies> getMoviesBySearchCriteria(String criteria) {
        return this._moviesRepository.findMovies(criteria);
    }

    public void addMovieToDatabase(Movies movie) {
        Movies mov = new Movies(movie.getName(), movie.getReview(), movie.getDescription(), movie.getImageURL());
        _moviesRepository.save(mov);
    }
}
