package com.challenge.controllers;

import com.challenge.dtos.CreateSellerRequest;
import com.challenge.entities.Seller;
import com.challenge.services.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/sellers")
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<Seller>> findAllSellers() {
        return ResponseEntity.ok(sellerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody @Valid CreateSellerRequest createSellerRequest) {
        return ResponseEntity.ok(sellerService.create(createSellerRequest));
    }
}
