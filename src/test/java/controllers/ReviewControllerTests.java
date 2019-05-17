package controllers;

import com.example.gmdbProject.Models.Movie;
import com.example.gmdbProject.Repository.MoviesRepository;
import com.example.gmdbProject.Repository.ReviewRepository;
import com.example.gmdbProject.Services.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewService.class)
@AutoConfigureMockMvc
@ComponentScan("com.example.gmdbProject")
public class ReviewControllerTests {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    MockMvc mockMvc;
    Movie movie = null;

    @Before
    @Transactional
    @Rollback
    public void setUp() throws Exception {
        movie = new Movie();
        movie.setTitle("hello");
        movie.setActors("hard");
        movie.setGenre("Action");
        movie.setLanguage("hindi");
        movie1 = new Movie();
        movie1.setTitle("hello");
        movie1.setActors("hard");
        movie1.setGenre("Action");
        movie1.setLanguage("hindi");
        moviesRepository.save(movie);
        moviesRepository.save(movie1);
    }

    @Test
    @Transactional
    @Rollback
    public void getMoviesByTitle() throws Exception {
        mockMvc.perform(get("/movies?criteria=title&value=Hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("hello")));
    }

    @Test
    @Transactional
    @Rollback
    public void getMovieByActors() throws Exception {
        mockMvc.perform(get("/movies?criteria=actors&value=hard"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].actors", is("hard")));
    }

    @Test
    @Transactional
    @Rollback
    public void getMovieByGenre() throws Exception {
        mockMvc.perform(get("/movies?criteria=genre&value=action"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].genre", is("Action")));
    }

    @Test
    @Transactional
    @Rollback
    public void getMovieByLanguage() throws Exception {
        mockMvc.perform(get("/movies?criteria=language&value=hindi"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].language", is("hindi")));
    }

        @Test
        @Transactional
        @Rollback
        public void getAllMovies () throws Exception {
        mockMvc.perform(get("/allMovies"))
                    .andExpect(status().isOk())
                    .andExpect((jsonPath("$", hasSize(2))));
        }

    }

