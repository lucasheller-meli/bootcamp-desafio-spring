package com.challenge.controllers;

import com.challenge.dtos.CreateUserDTO;
import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.services.FollowService;
import com.challenge.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FollowService followService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.save(createUserDTO));
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followedId) {
        followService.follow(followerId, followedId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/follows")
    public ResponseEntity<List<Follow>> follows() {
        return ResponseEntity.ok(followService.follows());
    }
}
