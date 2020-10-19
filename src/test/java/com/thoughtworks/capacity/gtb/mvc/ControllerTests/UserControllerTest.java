package com.thoughtworks.capacity.gtb.mvc.ControllerTests;

import com.fasterxml.jackson.annotation.JsonValue;
import com.thoughtworks.capacity.gtb.mvc.Repository.UserRepository;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        user = User.builder()
                .username("yangqian")
                .password("12345")
                .email("74@tw.com")
                .build();
    }

    @Test
    void should_return_none_response_when_register_user_success() throws Exception {
        String jsonValue =  "{\"username\":\"yangqian\", \"password\":\"12345\", \"email\":\"74@tw.com\"}";
        mockMvc.perform(post("/register")
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Optional<User> userOptional = userRepository.findByUsername("yangqian");
        assertTrue(userOptional.isPresent());
    }

    @Test
    void should_throw_bad_request_when_register_username_is_existed() throws Exception {
        userRepository.save(user);
        String jsonValue =  "{\"username\":\"yangqian\", \"password\":\"12345\", \"email\":\"74@tw.com\"}";
        mockMvc.perform(post("/register")
                .content(jsonValue).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_user_info_when_login_success() throws Exception {
        userRepository.save(user);
        mockMvc.perform(get("/login?username=yangqian&password=12345"))
                .andExpect(jsonPath("$.username", is("yangqian")));
    }

    @Test
    void should_throw_bad_request_when_login_failed() throws Exception {
        mockMvc.perform(get("/login?username=yangqian&password=12345"))
                .andExpect(status().isBadRequest());
    }
}
