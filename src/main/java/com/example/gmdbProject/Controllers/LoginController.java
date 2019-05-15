package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.Models.Login;
import com.example.gmdbProject.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class LoginController {
    private final LoginService _service;

    @Autowired
    public LoginController(LoginService service){
        this._service = service;
    }

    @PostMapping(value = "/login")
    public void confirmLogin(@RequestBody Login login, HttpServletResponse response) throws IOException {
        String val = _service.confirmLogin(login.getEmail(), login.getPassword());
        System.out.println(val);
        if(val.equals("User Not Found")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User Not Found");
        }
        else if(val.equals("Successfully logged in"))
        {
            response.setStatus(HttpServletResponse.SC_ACCEPTED, "Successfully logged in");
        }
        else{
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Incorrect Password");
        }
    }

}
