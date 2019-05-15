package com.example.gmdbProject.DTOs;
import lombok.Data;

@Data
public class ReviewDto {
    private String email;
    private String reviewText;
    private String movieTitle;
    private String reviewTitle;
}
