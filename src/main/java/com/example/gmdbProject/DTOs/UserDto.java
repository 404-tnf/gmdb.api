package com.example.gmdbProject.DTOs;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String screenName;
    private String password;
    private String repeatPassword;
}
