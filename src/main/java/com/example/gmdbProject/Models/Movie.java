package com.example.gmdbProject.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="MOVIES")
@Data
public class Movie
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MOVIE_ID", unique = true, nullable = false)
    private Long MovieId;

    @Column(name="METASCORE")
    private String Metascore;

    @Column(name="BOXOFFICE")
    private String BoxOffice;

    @Column(name="WEBSITE")
    private String Website;

    @Column(name="IMDB_RATING")
    private String imdbRating;

    @Column(name="IMDB_VOTES")
    private String imdbVotes;

    @Column(name="RUNTIME")
    private String Runtime;

    @Column(name="LANGUAGE")
    private String Language;

    @Column(name="RATED")
    private String Rated;

    @Column(name="PRODUCTION")
    private String Production;

    @Column(columnDefinition = "date", name="RELEASED")
    @JsonFormat(pattern = "dd MMM yyyy")
    private Date Released;

    @Column(name="IMDBID")
    private String imdbid;

    @Column(name="PLOT",columnDefinition = "LONGTEXT")
    private String Plot;

    @Column(name="DIRECTOR")
    private String Director;

    @Column(name="TITLE")
    private String title;

    @Column(name="ACTORS")
    private String Actors;

    @Column(name="RESPONSE")
    private String Response;

    @Column(name="TYPE")
    private String Type;

    @Column(name="AWARDS")
    private String Awards;

    @Column(columnDefinition = "date", name="DVD")
    @JsonFormat(pattern = "dd MMM yyyy")
    private Date DVD;

    @Column(name="YEAR")
    private String Year;

    @Column(name="POSTER")
    private String Poster;

    @Column(name="COUNTRY")
    private String Country;

    @Column(name="GENRE")
    private String Genre;

    @Column(name="WRITER",columnDefinition = "LONGTEXT")
    private String Writer;

    @OneToMany(mappedBy = "movie", orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Review> reviews = new ArrayList<>();
}