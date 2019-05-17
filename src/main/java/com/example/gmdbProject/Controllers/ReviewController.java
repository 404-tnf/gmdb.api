package com.example.gmdbProject.Controllers;

import com.example.gmdbProject.DTOs.ReviewDto;

import com.example.gmdbProject.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin
@RestController
public class ReviewController {
    private final ReviewService _service;

    @Autowired
    public ReviewController(ReviewService service) {this._service =service;}

    @PostMapping(value = "/review")
    public String addReview(@RequestBody ReviewDto data, HttpServletResponse response) throws IOException {
        if(this._service.addReview(data)) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return "Review Added";
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,"review not added");
            return "";
        }
    }
}
