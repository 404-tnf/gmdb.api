package controllers;

import com.example.gmdbProject.Models.Login;
import com.example.gmdbProject.Models.User;
import com.example.gmdbProject.Repository.LoginRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.example.gmdbProject.Services.LoginService.class)
@AutoConfigureMockMvc
@ComponentScan("com.example.gmdbProject")
public class LoginControllerTests {

    @Autowired
    LoginRepository loginRepository;

    @Autowired
    MockMvc mockMvc;

    Login login = null;

    User user = null;

    @Before
    @Transactional
    @Rollback
    public void setUp() throws Exception {
        user = new User();
        user.setEmail("hardik@gmail.com");
        user.setPassword("1234");
        loginRepository.save(user);
    }

    @Test
    @Transactional
    @Rollback
    public void confirmLoginSuccesful() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.com\",\"password\" : \"1234\"}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully logged in"));
    }

    @Test
    @Transactional
    @Rollback
    public void confirmLoginNotFound() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.\",\"password\" : \"1234\"}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User Not Found"));
    }

    @Test
    @Transactional
    @Rollback
    public void confirmLoginIncorrectPassword() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.com\",\"password\" : \"123\"}";

        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Incorrect Password"));
    }


}
