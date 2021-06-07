package com.challenge.controllers;

import com.challenge.dtos.CountFollowDTO;
import com.challenge.dtos.FollowDTO;
import com.challenge.entities.User;
import com.challenge.exceptions.SellerNotFoundException;
import com.challenge.exceptions.UserNotFoundException;
import com.challenge.services.SellerService;
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
    private final SellerService sellerService;


    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }


//    US 0001
    @PostMapping("/{userId}/follow/{sellerId}")
    public ResponseEntity<Void> follow(@PathVariable Long userId, @PathVariable Long sellerId) throws UserNotFoundException, SellerNotFoundException {
        sellerService.follow(userId, sellerId);
        return ResponseEntity.ok().build();
    }

//    US 0002
    @GetMapping("/{sellerId}/followers/count")
    public ResponseEntity<CountFollowDTO> countFollow(@PathVariable Long sellerId) throws SellerNotFoundException {
        return ResponseEntity.ok(sellerService.countFollow(sellerId));
    }

//   US 0003
    @GetMapping("/{sellerId}/followers/list")
    public ResponseEntity<FollowDTO> followerList(@PathVariable Long sellerId, @RequestParam(required = false) String order) throws SellerNotFoundException{
        return ResponseEntity.ok(sellerService.followerList(sellerId, order));
    }

//    US 0004
    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<FollowDTO> followedList(@PathVariable Long userId, @RequestParam(required = false) String order) throws UserNotFoundException{
        return ResponseEntity.ok(userService.followedList(userId , order));
    }

//    US 0007
    @PostMapping("/{userId}/unfollow/{sellerId}")
    public ResponseEntity<Void> unfollow(@PathVariable Long userId, @PathVariable Long sellerId) throws UserNotFoundException, SellerNotFoundException{
        sellerService.unfollow(userId, sellerId);
        return ResponseEntity.ok().build();
    }
}