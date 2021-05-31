package com.challenge.controllers;

import com.challenge.dtos.CreateUserDTO;
import com.challenge.entities.User;
import com.challenge.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.save(createUserDTO));
    }
}
