package com.thoughtworks.capacity.gtb.mvc.ControllerTests;

import com.thoughtworks.capacity.gtb.mvc.Repository.UserRepository;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

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
}
