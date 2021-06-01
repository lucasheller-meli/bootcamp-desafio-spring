package com.challenge.controllers;

import com.challenge.dtos.CreateUserRequest;
import com.challenge.dtos.FollowedResponse;
import com.challenge.dtos.FollowersCountResponse;
import com.challenge.dtos.FollowersResponse;
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
    public ResponseEntity<User> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.save(createUserRequest));
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followedId) throws UserNotFound, SelfFollow, AlreadyFollowing {
        followService.follow(followerId, followedId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<FollowersCountResponse> followersCount(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(followService.followersCount(userId));
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<FollowersResponse> followers(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(followService.followers(userId));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedResponse> followed(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(followService.followed(userId));
    }
}
