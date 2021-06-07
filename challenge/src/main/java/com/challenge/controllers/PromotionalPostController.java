package com.challenge.controllers;

import com.challenge.dtos.CreatePromotionalPostRequest;
import com.challenge.dtos.PromotionalPostsCountResponse;
import com.challenge.dtos.PromotionalPostsResponse;
import com.challenge.entities.PromotionalPost;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.services.PromotionalPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class PromotionalPostController {
    private final PromotionalPostService promotionalPostService;

    @GetMapping("/promotional/posts")
    public ResponseEntity<List<PromotionalPost>> findAllPromotionalPosts() {
        return ResponseEntity.ok(promotionalPostService.findAll());
    }

    @PostMapping("/new/promotional/post")
    public ResponseEntity<Void> createPromotionalPost(@RequestBody @Valid CreatePromotionalPostRequest createPostRequest) throws SellerNotFound {
        promotionalPostService.create(createPostRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sellerId}/promotional/count")
    public ResponseEntity<PromotionalPostsCountResponse> promotionalPostsCount(@PathVariable Long sellerId) throws SellerNotFound {
        return ResponseEntity.ok(promotionalPostService.promotionalPostsCount(sellerId));
    }

    @GetMapping("/{sellerId}/promotional/list")
    public ResponseEntity<PromotionalPostsResponse> promotionalPosts(@PathVariable Long sellerId) throws SellerNotFound {
        return ResponseEntity.ok(promotionalPostService.promotionalPosts(sellerId));
    }
}
