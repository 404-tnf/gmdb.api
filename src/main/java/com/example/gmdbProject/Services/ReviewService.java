package com.example.gmdbProject.Services;

import com.example.gmdbProject.DTOs.ReviewDto;
import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Models.Review;
import com.example.gmdbProject.Models.User;
import com.example.gmdbProject.Repository.MoviesRepository;
import com.example.gmdbProject.Repository.RegisterRepository;
import com.example.gmdbProject.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository _reviewRepository;
    private final MoviesRepository _moviesRepository;
    private final RegisterRepository _registerRepository;

    @Autowired
    public ReviewService(RegisterRepository registerRepository, ReviewRepository reviewRepository, MoviesRepository moviesRepository) {
        this._registerRepository = registerRepository;
        this._reviewRepository = reviewRepository;
        this._moviesRepository = moviesRepository;
    }

    public boolean addReview(ReviewDto reviewDto){
        List<User> user = this._registerRepository.getUserBasedOnEmail(reviewDto.getEmail());
        List<Movie> movies = this._moviesRepository.findMoviesName(reviewDto.getMovieTitle());

        if (user.size() > 0 && movies.size() > 0) {
            Review review = new Review();
            review.setMovie(movies.get(0));
            review.setUser(user.get(0));
            review.setReviewTitle(reviewDto.getReviewTitle());
            review.setReviewText((reviewDto.getReviewText()));
            review.setLastUpdated(new Date());
            this._reviewRepository.save(review);
            return true;
        }
        return false;
    }

}
