package com.challenge.controllers;

import com.challenge.dtos.CreateUserDTO;
import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.UserNotFound;
import com.challenge.services.FollowService;
import com.challenge.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final FollowService followService;

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return ResponseEntity.ok(userService.save(createUserDTO));
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followedId) throws UserNotFound, SelfFollow, AlreadyFollowing {
        followService.follow(followerId, followedId);
        return ResponseEntity.ok().build();
    }

    // US 0002

    // US 0003 - FALTA RETORNAR O OBJETO CERTO E OUTROS STATUS CODES
    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<List<Follow>> followers(@PathVariable Long userId) {
        return ResponseEntity.ok(followService.followers(userId));
    }
}
