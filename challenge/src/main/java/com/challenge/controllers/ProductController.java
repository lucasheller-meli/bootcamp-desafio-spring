package com.challenge.controllers;

import com.challenge.dtos.*;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.entities.PromotionalPost;
import com.challenge.exceptions.UserNotFound;
import com.challenge.services.PostService;
import com.challenge.services.ProductService;
import com.challenge.services.PromotionalPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final PostService postService;
    private final PromotionalPostService promotionalPostService;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping("/new/post")
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) throws UserNotFound {
        postService.create(createPostRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedPostsResponse> followedPosts(@PathVariable Long userId, @RequestParam(required = false) String order) throws UserNotFound {
        return ResponseEntity.ok(postService.followedPosts(userId, order));
    }

    @GetMapping("/promotional/posts")
    public ResponseEntity<List<PromotionalPost>> findAllPromotionalPosts() {
        return ResponseEntity.ok(promotionalPostService.findAll());
    }

    @PostMapping("/new/promotional/post")
    public ResponseEntity<Void> createPromotionalPost(@RequestBody @Valid CreatePromotionalPostRequest createPostRequest) throws UserNotFound {
        promotionalPostService.create(createPostRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/promotional/count")
    public ResponseEntity<PromotionalPostsCountResponse> promotionalPostsCount(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(promotionalPostService.promotionalPostsCount(userId));
    }

    @GetMapping("/{userId}/promotional/list")
    public ResponseEntity<PromotionalPostsResponse> promotionalPosts(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(promotionalPostService.promotionalPosts(userId));
    }
}
