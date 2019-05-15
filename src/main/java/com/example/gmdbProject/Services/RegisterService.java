package com.example.gmdbProject.Services;

import com.example.gmdbProject.DTOs.TempDto;
import com.example.gmdbProject.DTOs.UserDto;
import com.example.gmdbProject.Models.User;
import com.example.gmdbProject.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    private final RegisterRepository _registerRepository;

    @Autowired
    public RegisterService(RegisterRepository registerRepository) {
        this._registerRepository = registerRepository;
    }

    public String isSaveSuccessful(UserDto user) {
        System.out.println("Aman:"+user);
        if(user.getPassword().equals(user.getRepeatPassword())) {
            if(this._registerRepository.getUserBasedOnEmail(user.getEmail()).size() <= 0) {
                User newUser = new User();
                newUser.setEmail(user.getEmail());
                newUser.setPassword(user.getPassword());
                newUser.setRepeatPassword((user.getRepeatPassword()));
                newUser.setScreenName(user.getScreenName());
                newUser.setReviews(null);
                this._registerRepository.save(newUser);
                return "User Created";
            }
            return "User Already Exits";
        }
        return "Password Mismatch";
    }

    public boolean hasUserUpdated(TempDto data) {
        List<User> userList = this._registerRepository.getUserBasedOnEmail(data.getEmail());
        if(userList.size() > 0) {
            int val = this._registerRepository.updateUserPassword(data.getEmail(), data.getUpdatedPassword());
            return true;
        }
        return false;
    }
}
