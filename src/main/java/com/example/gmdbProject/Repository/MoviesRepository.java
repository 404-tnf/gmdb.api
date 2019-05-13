package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.Movies;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface MoviesRepository extends CrudRepository<Movies, Long>{

    @Query("SELECT m FROM Movies m WHERE m.name = :name")
    List<Movies> findMovies(@Param("name") String name);

}
