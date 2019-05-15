package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = :email")
    List<User> findUser(@Param("email") String email);
}
