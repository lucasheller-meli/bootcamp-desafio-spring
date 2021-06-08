package com.desafio.controller;

import com.desafio.dtos.FollowDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.exceptions.IdNotFound;
import com.desafio.exceptions.NotFollow;
import com.desafio.service.SellerService;
import com.desafio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SellerService sellerService;

    public UserController(UserService userService, SellerService sellerService){
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @PostMapping("/{idUser}/follow/{idSeller}")
    public ResponseEntity<Void> follow(@PathVariable Long idUser, @PathVariable Long idSeller) throws IdNotFound {
        userService.follow(idUser, idSeller);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idSeller}/followers/count")
    public ResponseEntity<QuantityDTO> quantityFollowers(@PathVariable Long idSeller) throws IdNotFound{
        return ResponseEntity.ok(sellerService.quantityFollowers(idSeller));
    }

    @GetMapping("/{idSeller}/followers/list")
    public ResponseEntity<FollowDTO> listFollowers(@PathVariable Long idSeller, @RequestParam(required = false) String order) throws IdNotFound{
        return ResponseEntity.ok(sellerService.listFollowers(idSeller, order));
    }

    @GetMapping("/{idUser}/followed/list")
    public ResponseEntity<FollowDTO> listFollowing(@PathVariable Long idUser, @RequestParam(required = false) String order) throws IdNotFound{
        return ResponseEntity.ok(userService.listFollowing(idUser, order));
    }

    @DeleteMapping("/{idUser}/unfollow/{idSeller}")
    public ResponseEntity<Void> unfollow(@PathVariable Long idUser, @PathVariable Long idSeller) throws IdNotFound, NotFollow{
        userService.unfollow(idUser, idSeller);
        return ResponseEntity.ok().build();
    }


}
