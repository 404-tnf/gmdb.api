package com.example.gmdbProject.DTOs;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String validatePassword;
}
