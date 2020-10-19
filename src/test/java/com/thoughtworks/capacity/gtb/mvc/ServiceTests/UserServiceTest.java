package com.thoughtworks.capacity.gtb.mvc.ServiceTests;

import com.thoughtworks.capacity.gtb.mvc.Repository.UserRepository;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.RequestNotValidException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@SpringBootTest
class UserServiceTest {

    private UserService userService;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void should_return_none_body_when_register_user_success() {
        User user = User.builder()
                .username("yangqian")
                .password("123")
                .email("743295483@qq.com")
                .build();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        userService.register(user);
        verify(userRepository).save(user);
    }

    @Test
    void should_throw_exception_when_register_user_is_existed() {
        User user = User.builder()
                .username("yangqian")
                .password("123")
                .email("743295483@qq.com")
                .build();
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));
        RequestNotValidException exception = assertThrows(RequestNotValidException.class, () -> userService.register(user));
        assertEquals("username is existed!", exception.getMessage());
    }
}
