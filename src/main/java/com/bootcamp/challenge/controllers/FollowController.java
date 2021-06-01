package com.bootcamp.challenge.controllers;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;
import com.bootcamp.challenge.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(value = "/{userId}/{userIdToFollow}")
    private ResponseEntity<Void> followSeller(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "userIdToFollow") Integer followedUser){
        followService.followUser(userId, followedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/followers/count")
    private ResponseEntity<UserCountFollowersResponse> countFollowers(@PathVariable(value = "userId") Integer userId){
        return ResponseEntity.ok(followService.countFollowers(userId));
    }

    @GetMapping(value = "/{userId}/followers/list")
    private ResponseEntity<UserFollowersResponse> listFollowers(@PathVariable(value = "userId") Integer userId){
        return ResponseEntity.ok(followService.listFollowers(userId));
    }




}
