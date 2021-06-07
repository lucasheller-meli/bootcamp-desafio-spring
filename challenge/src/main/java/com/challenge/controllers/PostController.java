package com.challenge.controllers;

import com.challenge.dtos.CreatePostRequest;
import com.challenge.dtos.FollowedPostsResponse;
import com.challenge.entities.Post;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.exceptions.UserNotFound;
import com.challenge.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPosts() {
        return ResponseEntity.ok(postService.findAll());
    }

    @PostMapping("/new/post")
    public ResponseEntity<Void> createPost(@RequestBody @Valid CreatePostRequest createPostRequest) throws SellerNotFound {
        postService.create(createPostRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<FollowedPostsResponse> followedPosts(@PathVariable Long userId, @RequestParam(required = false) String order) throws UserNotFound {
        return ResponseEntity.ok(postService.followedPosts(userId, order));
    }
}
