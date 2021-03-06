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
    public String confirmLogin(@RequestBody Login login, HttpServletResponse response) throws IOException
    {
        String val = _service.confirmLogin(login.getEmail(), login.getPassword());
        if(val.equals("User Not Found")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "User Not Found";
        }
        else if(val.equals("Incorrect Password"))
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Incorrect Password";
        }
        else{
            return val;
        }
    }
}
