package com.example.gmdbProject.Services;

import com.example.gmdbProject.Models.User;
import com.example.gmdbProject.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    private final LoginRepository _loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this._loginRepository = loginRepository;
    }

    public String confirmLogin(String email, String password ) {
        List<User> user  = this._loginRepository.findUser(email);
        System.out.println(user);
        if (user.size() ==  0 ) {
            return "User Not Found";
        }
        return (user.get(0).getPassword().equals(password))? "Successfully logged in - "+user.get(0).getScreenName() : "Incorrect Password";
    }
}
