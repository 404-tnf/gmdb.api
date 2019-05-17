package com.example.gmdbProject.DTOs;

import com.example.gmdbProject.Models.Review;
import lombok.Data;

import java.util.Date;

@Data
public class MovieReviewDto {
    public Long MovieId;
    public String Metascore;
    public String BoxOffice;
    public String Website;
    public String imdbRating;
    public String imdbVotes;
    public String Runtime;
    public String Language;
    public String Rated;
    public String Production;
    public String imdbid;
    public Date Released;
    public String Plot;
    public String Director;
    public String title;
    public String Actors;
    public String Response;
    public String Type;
    public String Awards;
    public Date DVD;
    public String Year;
    public String Poster;
    public String Country;
    public String Genre;
    public String Writer;
    public Iterable<Review> reviews;
}

