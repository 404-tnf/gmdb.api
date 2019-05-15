package com.example.gmdbProject.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Accessors(chain = true)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    User user;

    @Column(columnDefinition="LONGTEXT")
    String reviewText;

    @Column(columnDefinition = "TEXT")
    String reviewTitle;

    @Column(columnDefinition = "date", name="lastUpdated")
    @JsonFormat(pattern = "yyyy-MM-dd")
    Date lastUpdated;
}
