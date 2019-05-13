package com.example.gmdbProject.Models;

import javax.persistence.*;

@Entity
@Table(name = "Movies")
public class Movies {


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String review;
    private String description;
    private String imageURL;

    public Movies() {

    }

    public Movies(String name, String review, String description, String imageURL) {
        this.name = name;
        this.review = review;
        this.description = description;
        this.imageURL = imageURL;
    }

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public String getReview() {
        return review;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

}
