package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.Repository.UserRepository;
import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(User user) {
        user.setId(userRepository.getMaxUserId());
        userRepository.save(user);
        userRepository.setMaxUserId();
    }
}
