package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movie, Long>{

    @Query("SELECT m FROM MOVIES m where m.title like concat('%',:value,'%')")
    List<Movie> findMoviesTitle(@Param("value") String value);

    @Query("SELECT m FROM MOVIES m where m.Actors like concat('%',:value,'%')")
    List<Movie> findMoviesActors(@Param("value") String value);

    @Query("SELECT m FROM MOVIES m where m.Genre like concat('%',:value,'%')")
    List<Movie> findMoviesGenre(@Param("value") String value);

    @Query("SELECT m FROM MOVIES m where m.Language like concat('%',:value,'%')")
    List<Movie> findMoviesLanguage(@Param("value") String value);

    @Query("SELECT m FROM MOVIES m where m.title = :name")
    List<Movie> findMoviesName(@Param("name") String name);



}

