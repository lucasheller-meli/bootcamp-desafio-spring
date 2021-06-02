package com.challenge.controllers;

import com.challenge.dtos.CreatePostRequest;
import com.challenge.dtos.FollowedPostsResponse;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.exceptions.UserNotFound;
import com.challenge.services.PostService;
import com.challenge.services.ProductService;
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
    public ResponseEntity<Void> createPostAndProduct(@RequestBody @Valid CreatePostRequest createPostRequest) throws UserNotFound {
        postService.create(createPostRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedPostsResponse> followedPosts(@PathVariable Long userId) throws UserNotFound {
        return ResponseEntity.ok(postService.followedPosts(userId));
    }
}
