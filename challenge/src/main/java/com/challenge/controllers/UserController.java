package com.challenge.controllers;

import com.challenge.dtos.CreateUserRequest;
import com.challenge.dtos.FollowedResponse;
import com.challenge.dtos.FollowersCountResponse;
import com.challenge.dtos.FollowersResponse;
import com.challenge.entities.User;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.SellerNotFound;
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

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.create(createUserRequest));
    }
}
