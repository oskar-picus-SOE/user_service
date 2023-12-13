package com.example.user_service.service;

import com.example.user_service.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserService {
    @Value("${user.repository.url}")
    private String userRepositoryUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<User> findUser(String username) {
        User[] users = restTemplate.getForObject(userRepositoryUrl, User[].class);

        if (users == null) {
            return Optional.empty();
        }

        return Arrays.stream(users)
                .filter(user -> user.username().equals(username))
                .findAny();
    }
}
