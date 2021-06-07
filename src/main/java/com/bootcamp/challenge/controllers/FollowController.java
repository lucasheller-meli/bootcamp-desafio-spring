package com.bootcamp.challenge.controllers;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;
import com.bootcamp.challenge.services.FollowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping(value = "/{userId}/{userIdToFollow}")
    @ApiOperation(value = "Seguir um vendedor.")
    private ResponseEntity<Void> followSeller(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "userIdToFollow") Integer followedUser) {
        followService.followUser(userId, followedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}/followers/count")
    @ApiOperation(value = "Conta os seguidores.")
    private ResponseEntity<UserCountFollowersResponse> countFollowers(@PathVariable(value = "userId") Integer userId) {
        return ResponseEntity.ok(followService.countFollowers(userId));
    }

    @GetMapping(value = "/{userId}/followers/list")
    @ApiOperation(value = "Lista os meus seguidores.")
    private ResponseEntity<UserFollowersResponse> listFollowers(@PathVariable(value = "userId") Integer userId,
                                                                @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction

    ) {
        return ResponseEntity.ok(followService.listFollowers(userId, direction));
    }

    @GetMapping(value = "/{userId}/followed/list")
    @ApiOperation(value = "Lista quem eu sigo.")
    private ResponseEntity<UserFollowedResponse> listFollowed(@PathVariable(value = "userId") Integer userId,
                                                              @RequestParam(value = "direction", required = false, defaultValue = "asc") String direction) {
        return ResponseEntity.ok(followService.listFollowed(userId, direction));
    }

    @DeleteMapping(value = "/{userId}/{userIdToUnfollow}")
    @ApiOperation(value = "Parar de seguir um vendedor.")
    private ResponseEntity<Void> unfollowSeller(@PathVariable(value = "userId") Integer userId, @PathVariable(value = "userIdToUnfollow") Integer unfollowedUser) {
        followService.unfollowSeller(userId, unfollowedUser);
        return ResponseEntity.noContent().build();
    }


}
