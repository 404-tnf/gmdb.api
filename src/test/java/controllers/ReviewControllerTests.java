package controllers;


import com.example.gmdbProject.Repository.ReviewRepository;
import com.example.gmdbProject.Services.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewService.class)
@AutoConfigureMockMvc
@ComponentScan("com.example.gmdbProject")
public class ReviewControllerTests {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MockMvc mockMvc;



    @Test
    @Transactional
    public void addReview() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.com\",\"reviewText\" : \"new extra review\", \"reviewTitle\" : \"title extra\", \"movieTitle\" : \"The Godfather\"}";

        mockMvc.perform(post("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isAccepted())
                .andExpect(content().string("Review Added"));
    }

    @Test
    @Transactional
    public void reviewNotAdded() throws Exception {
        String json = "{\"email\" : \"hardik1@gmail.com\",\"reviewText\" : \"new extra review\", \"reviewTitle\" : \"title extra\", \"movieTitle\" : \"The Godfather\"}";

        mockMvc.perform(post("/review")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("review not added"));
    }
}
