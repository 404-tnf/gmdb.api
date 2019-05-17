package com.example.gmdbProject.Services;

import com.example.gmdbProject.DTOs.MovieReviewDto;
import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Models.Review;
import com.example.gmdbProject.Repository.MoviesRepository;
import com.example.gmdbProject.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {

    private final MoviesRepository _moviesRepository;
    private final ReviewRepository _reviewRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository, ReviewRepository reviewRepository) {
        this._moviesRepository = moviesRepository;
        this._reviewRepository = reviewRepository;
    }

    public Iterable<MovieReviewDto> getMoviesSearchCriteria(String criteria, String value) {
        Iterable<Movie> listOfMovies;
        if ((criteria != null && !criteria.isEmpty()) && (value != null && !value.isEmpty())) {
            switch (criteria) {
                case "title":
                    listOfMovies = this._moviesRepository.findMoviesTitle(value);
                    break;
                case "actors":
                    listOfMovies = this._moviesRepository.findMoviesActors(value);
                    break;
                case "genre":
                    listOfMovies = this._moviesRepository.findMoviesGenre(value);
                    break;
                case "language":
                    listOfMovies = this._moviesRepository.findMoviesLanguage(value);
                    break;
                default:
                    listOfMovies = this.getAllMovies();
                    break;
            }
            return this.movieReviewIterator(listOfMovies);
        }
        else {
            return this.movieReviewIterator(this.getAllMovies());
        }
    }

    public Iterable<Movie> getAllMovies() {
        return _moviesRepository.findAll();
    }

    private MovieReviewDto movieReviewMapper(Movie singleMovie, Iterable<Review> listOfReviews) {
        MovieReviewDto movieReviewDto = new MovieReviewDto();

        movieReviewDto.setMovieId(singleMovie.getMovieId());
        movieReviewDto.setMetascore(singleMovie.getMetascore());
        movieReviewDto.setBoxOffice(singleMovie.getBoxOffice());
        movieReviewDto.setWebsite(singleMovie.getWebsite());
        movieReviewDto.setImdbRating(singleMovie.getImdbRating());
        movieReviewDto.setImdbVotes(singleMovie.getImdbVotes());
        movieReviewDto.setRuntime(singleMovie.getRuntime());
        movieReviewDto.setLanguage(singleMovie.getLanguage());
        movieReviewDto.setRated(singleMovie.getRated());
        movieReviewDto.setProduction(singleMovie.getProduction());
        movieReviewDto.setImdbid(singleMovie.getImdbid());
        movieReviewDto.setReleased(singleMovie.getReleased());
        movieReviewDto.setPlot(singleMovie.getPlot());
        movieReviewDto.setDirector(singleMovie.getDirector());
        movieReviewDto.setTitle(singleMovie.getTitle());
        movieReviewDto.setActors(singleMovie.getActors());
        movieReviewDto.setResponse(singleMovie.getResponse());
        movieReviewDto.setType(singleMovie.getType());
        movieReviewDto.setAwards(singleMovie.getAwards());
        movieReviewDto.setDVD(singleMovie.getDVD());
        movieReviewDto.setYear(singleMovie.getYear());
        movieReviewDto.setPoster(singleMovie.getPoster());
        movieReviewDto.setCountry(singleMovie.getCountry());
        movieReviewDto.setGenre(singleMovie.getGenre());
        movieReviewDto.setWriter(singleMovie.getWriter());
        movieReviewDto.setReviews(listOfReviews);

        return movieReviewDto;
    }

    private Iterable<MovieReviewDto> movieReviewIterator(Iterable<Movie> listOfMovies) {
        Iterable<Review> listOfReviews;
        Iterable<MovieReviewDto> listOfMoviesWithReviews = new ArrayList<>();
        for (Movie singleMovie : listOfMovies) {
            listOfReviews = this._reviewRepository.findReviewsByMovieId(singleMovie.getMovieId());
            ((ArrayList<MovieReviewDto>) listOfMoviesWithReviews).add(this.movieReviewMapper(singleMovie, listOfReviews));
        }
        return listOfMoviesWithReviews;
    }
}
