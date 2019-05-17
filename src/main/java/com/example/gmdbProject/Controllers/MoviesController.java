package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.DTOs.MovieReviewDto;
import com.example.gmdbProject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MoviesController {

    private final MovieService _service;

    @Autowired
    public MoviesController(MovieService service){
        this._service = service;
    }

    @GetMapping(value = "/movies")
    public Iterable<MovieReviewDto> getMovies(@RequestParam(value="criteria", required = false) String criteria, @RequestParam(value="value", required = false) String value) {
        return _service.getMoviesSearchCriteria(criteria,value);
    }

//    @GetMapping(value = "/allMovies")
//    public Iterable<Movie> allMovies() {
//        return _service.getAllMovies();
//    }
}
