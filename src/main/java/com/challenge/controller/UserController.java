package com.challenge.controller;

import com.challenge.model.followers.FollowedDTO;
import com.challenge.model.followers.FollowersDTO;
import com.challenge.service.RetailerService;
import com.challenge.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/{client}/follow/{retailer}")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@PathVariable Integer client, @PathVariable Integer retailer) {
        userService.follow(client,retailer);
    }

    @PostMapping("/{client}/unfollow/{retailer}")
    @ResponseStatus(HttpStatus.OK)
    public void unfollow(@PathVariable Integer client, @PathVariable Integer retailer) {
        userService.unfollow(client,retailer);
    }

    @GetMapping("/{retailerId}/followers/count")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Integer> numberOfFollowers(@PathVariable Integer retailerId) {
        return new ResponseEntity<>(userService.countFollowers(retailerId), HttpStatus.OK);
    }

    @GetMapping("/{retailerId}/followers/list")
    @ResponseStatus(HttpStatus.OK)
    public FollowersDTO listOfFollowers(@PathVariable Integer retailerId, @RequestParam(required = false) String order) {
        return userService.whoFollowMe(retailerId,order);
    }

    @GetMapping("/{userId}/followed/list")
    @ResponseStatus(HttpStatus.OK)
    public FollowedDTO whoAmIFollowing(@PathVariable Integer userId){
        return userService.whoAmIFollowing(userId);
    }

}
