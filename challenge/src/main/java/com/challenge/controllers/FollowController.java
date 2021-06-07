package com.challenge.controllers;

import com.challenge.dtos.FollowedResponse;
import com.challenge.dtos.FollowersCountResponse;
import com.challenge.dtos.FollowersResponse;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.exceptions.UserNotFound;
import com.challenge.services.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    @PostMapping("/{followerId}/follow/{followedId}")
    public ResponseEntity<Void> follow(@PathVariable Long followerId, @PathVariable Long followedId) throws UserNotFound, SellerNotFound, SelfFollow, AlreadyFollowing {
        followService.follow(followerId, followedId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sellerId}/followers/count")
    public ResponseEntity<FollowersCountResponse> followersCount(@PathVariable Long sellerId) throws SellerNotFound {
        return ResponseEntity.ok(followService.followersCount(sellerId));
    }

    @GetMapping("/{sellerId}/followers/list")
    public ResponseEntity<FollowersResponse> followers(@PathVariable Long sellerId, @RequestParam(required = false) String order) throws SellerNotFound {
        return ResponseEntity.ok(followService.followers(sellerId, order));
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowedResponse> followed(@PathVariable Long userId, @RequestParam(required = false) String order) throws UserNotFound {
        return ResponseEntity.ok(followService.followed(userId, order));
    }

    @PostMapping("/{followerId}/unfollow/{followedId}")
    public ResponseEntity<Void> unfollow(@PathVariable Long followerId, @PathVariable Long followedId) {
        followService.unfollow(followerId, followedId);
        return ResponseEntity.ok().build();
    }
}
