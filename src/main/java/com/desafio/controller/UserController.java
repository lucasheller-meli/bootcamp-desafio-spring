package com.desafio.controller;

import com.desafio.dtos.FollowDTO;
import com.desafio.dtos.PostDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.entities.Posters;
import com.desafio.service.SellerService;
import com.desafio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SellerService sellerService;

    public UserController(UserService userService, SellerService sellerService){
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping("/{idUser}/follow/{idSaler}")
    public ResponseEntity<Void> follow(@PathVariable Long idUser, @PathVariable Long idSaler){
        userService.follow(idUser, idSaler);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idSeller}/followers/count")
    public ResponseEntity<QuantityDTO> quantityFollowers(@PathVariable Long idSeller){
        return ResponseEntity.ok(sellerService.quantityFollowers(idSeller));
    }

    @GetMapping("/{idSeller}/followers/list")
    public ResponseEntity<FollowDTO> listFollowers(@PathVariable Long idSeller){
        return ResponseEntity.ok(sellerService.listFollowers(idSeller));
    }

    @GetMapping("/{idUser}/followed/list")
    public ResponseEntity<FollowDTO> listFollowing(@PathVariable Long idUser){
        return ResponseEntity.ok(userService.listFollowing(idUser));
    }

    @PostMapping("/product/newpost")
    public ResponseEntity<Posters> post(@RequestBody PostDTO postDTO){
        return ResponseEntity.ok(sellerService.post(postDTO));
    }

    @DeleteMapping("/{idUser}/unfollow/{idSeller}")
    public ResponseEntity<Void> unfollow(@PathVariable Long idUser, @PathVariable Long idSeller){
        userService.unfollow(idUser, idSeller);
        return ResponseEntity.ok().build();
    }
}
