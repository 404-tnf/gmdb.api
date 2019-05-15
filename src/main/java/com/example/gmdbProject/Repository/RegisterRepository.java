package com.example.gmdbProject.Repository;

import com.example.gmdbProject.Models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RegisterRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u where u.email = :email")
    List<User> getUserBasedOnEmail(@Param("email") String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = :updatedPassword, u.repeatPassword = :updatedPassword where u.email = :email")
    int updateUserPassword(@Param("email") String email, @Param("updatedPassword") String password);
}

