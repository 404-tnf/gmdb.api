package controllers;


import com.example.gmdbProject.Models.Login;
import com.example.gmdbProject.Models.User;
import com.example.gmdbProject.Repository.RegisterRepository;
import com.example.gmdbProject.Services.RegisterService;
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

public class RegisterControllerTests {
    @Autowired
    RegisterService registerService;

    @Autowired
    RegisterRepository registerRepository;

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
        user.setPassword("11234");
        user.setRepeatPassword("11234");
        user.setScreenName("Hardshiv");
        registerRepository.save(user);

    }


    @Test
    @Transactional
    @Rollback
    public void confirmUserCreated() throws Exception {
        String json = "{\"email\" : \"hardik1@gmail.com\",\"password\" : \"11234\", \"validatePassword\" : \"11234\", \"firstName\" : \"Hardshiv\",\"lastName\" : \"Hardshiv\" }";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("User Created"));
    }

    @Test
    @Transactional
    @Rollback
    public void confirmUserAlreadyExist() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.com\",\"password\" : \"11234\", \"validatePassword\" : \"11234\", \"firstName\" : \"Hardshiv\",\"lastName\" : \"Hardshiv\" }";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User already present"));
    }

    @Test
    @Transactional
    @Rollback
    public void passwordShouldMatch() throws Exception {
        String json = "{\"email\" : \"hardik@gmail.com\",\"password\" : \"11234\", \"validatePassword\" : \"1234\", \"firstName\" : \"Hardshiv\",\"lastName\" : \"Hardshiv\" }";

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Password and Repeat Password Should Match"));
    }
}

