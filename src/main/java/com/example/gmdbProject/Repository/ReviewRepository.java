package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {

}
