package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class MoviesController {

    private final MovieService _service;

    @Autowired
    public MoviesController(MovieService service){
        this._service = service;
    }

    @GetMapping(value = "/movies")
    public Iterable<Movie> getMovies(@RequestParam(value="criteria", required = false) String criteria, @RequestParam(value="value", required = false) String value) {
        return _service.getMoviesSearchCriteria(criteria,value);
    }

    @GetMapping(value = "/allMovies")
    public Iterable<Movie> allMovies() {
        return _service.getAllMovies();
    }
}
