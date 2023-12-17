package com.example.user_service.controller;

import com.example.user_service.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.user_service.service.UserService;

@RestController
@RequestMapping("api")
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Value("${server.port}")
    private String port;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<User> findUser(@RequestParam("username") String username) {
        LOGGER.info("Finding user with username {} on instance running on port {}", username, port);
        return userService.findUser(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
