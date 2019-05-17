package com.example.gmdbProject.Services;

import com.example.gmdbProject.DTOs.MovieReviewDto;
import com.example.gmdbProject.DTOs.ReviewDto;
import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Models.Review;
import com.example.gmdbProject.Repository.MoviesRepository;
import com.example.gmdbProject.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MoviesRepository _moviesRepository;
    private final ReviewRepository _reviewRepository;

    @Autowired
    public MovieService(MoviesRepository moviesRepository, ReviewRepository reviewRepository) {
        this._moviesRepository = moviesRepository;
        this._reviewRepository = reviewRepository;
    }

    public List<MovieReviewDto> getMoviesSearchCriteria(String criteria, String value) {
        List<Movie> listOfMovies;
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

    public List<Movie> getAllMovies() {
        return (ArrayList<Movie>)_moviesRepository.findAll();
    }

    private MovieReviewDto movieReviewMapper(Movie singleMovie, Iterable<Review> listOfReviews) {
        List<ReviewDto> listOfReviewLocal  = new ArrayList<>();
        if(listOfReviews.spliterator().getExactSizeIfKnown() > 0) {
            listOfReviews.forEach(review -> {
                ReviewDto newReview = new ReviewDto();
                newReview.setReviewTitle(review.getReviewTitle());
                newReview.setReviewText(review.getReviewText());
                newReview.setEmail(review.getUser().getEmail());
                newReview.setMovieTitle(review.getMovie().getTitle());
                listOfReviewLocal.add(newReview);
            });
        }
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
        movieReviewDto.setReviews(listOfReviewLocal);

        return movieReviewDto;
    }

    private List<MovieReviewDto> movieReviewIterator(Iterable<Movie> listOfMovies) {
        List<Review> listOfReviews;
        List<MovieReviewDto> listOfMoviesWithReviews = new ArrayList<>();
        for (Movie singleMovie : listOfMovies) {
            listOfReviews = this._reviewRepository.findReviewsByMovieId(singleMovie.getMovieId());
            listOfMoviesWithReviews.add(this.movieReviewMapper(singleMovie, listOfReviews));
        }
        return listOfMoviesWithReviews;
    }
}
