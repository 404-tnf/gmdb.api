package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.DTOs.Movie;
import com.example.gmdbProject.Models.Movies;
import com.example.gmdbProject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MoviesController {

    private final MovieService _service;

    @Autowired
    public MoviesController(MovieService service){
        this._service = service;
    }

    @GetMapping(value = "/movies")
    public List<Movies> getMovies(@RequestParam(value="name", required = true) String name) {
        return _service.getMoviesBySearchCriteria(name);
    }

    @PostMapping(value = "/movies")
    public void postMovies(@RequestBody Movie mov) {
        Movies movie = new Movies(mov.getName(),mov.getReview(), mov.getDescription(), mov.getImageURL());
        System.out.println("=========================================================" + movie.getId());
        _service.addMovieToDatabase(movie);
    }
}
