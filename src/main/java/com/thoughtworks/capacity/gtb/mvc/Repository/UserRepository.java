package com.thoughtworks.capacity.gtb.mvc.Repository;

import com.thoughtworks.capacity.gtb.mvc.dto.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {

    private Integer maxUserId = 1;

    private  Map<Integer, User> userMap = new HashMap<>();

    public void deleteAll() {
        userMap.clear();
    }

    public void save(User user) {
        userMap.put(user.getId(), user);
    }

    public Integer getMaxUserId() {
        return maxUserId;
    }

    public void setMaxUserId() {
        maxUserId++;
    }

    public Optional<User> findByUsername(String username) {
        for (Map.Entry<Integer, User> user: userMap.entrySet()) {
            if (user.getValue().getUsername().equals(username))
                return Optional.of(user.getValue());
        }
        return Optional.empty();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {
        for (Map.Entry<Integer, User> user: userMap.entrySet()) {
            if (user.getValue().getUsername().equals(username) && user.getValue().getPassword().equals(password))
                return Optional.of(user.getValue());
        }
        return Optional.empty();
    }
}
