package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.DTOs.TempDto;
import com.example.gmdbProject.DTOs.UserDto;
import com.example.gmdbProject.Services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class RegisterController {
    private final RegisterService _service;

    @Autowired
    public RegisterController(RegisterService service){
        this._service = service;
    }

    @PostMapping(value = "/register")
    public void create(@RequestBody UserDto data, HttpServletResponse response) throws IOException {
        String val = this._service.isSaveSuccessful(data);
        if(val.equals("User Created")) {
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        else if(val.equals("User Already Exits"))
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "User already present");
        }
        else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Password and Repeat Password Should Match");
        }
    }

    @PostMapping(value = "/forgotPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody TempDto data) {
        if (this._service.hasUserUpdated(data)){
            return "Password Updated";
        }
        else{
            return "User Not Found";
        }
    }

}
