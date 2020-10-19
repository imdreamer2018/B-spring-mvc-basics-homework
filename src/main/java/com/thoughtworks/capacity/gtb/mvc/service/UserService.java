package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Repository.UserRepository;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import com.thoughtworks.capacity.gtb.mvc.exception.RequestNotValidException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent())
            throw new RequestNotValidException("username is existed!");
        user.setId(userRepository.getMaxUserId());
        userRepository.save(user);
        userRepository.setMaxUserId();
    }
}
