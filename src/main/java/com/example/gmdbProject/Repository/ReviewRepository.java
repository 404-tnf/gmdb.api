package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
        @Query("SELECT r FROM Review r where r.movie.MovieId = :id")
        List<Review> findReviewsByMovieId(@Param("id") long id);
}
