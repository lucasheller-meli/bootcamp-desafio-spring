package com.challenge.services;

import com.challenge.dtos.CreatePostRequest;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void create(CreatePostRequest createPostRequest) throws UserNotFound {
        Product product = productService.create(createPostRequest.getProduct());
        User user = userService.findById(createPostRequest.getUserId());

        save(Post.builder()
                .product(product)
                .user(user)
                .category(createPostRequest.getCategory())
                .price(createPostRequest.getPrice())
                .build());
    }

    private void save(Post post) {
        postRepository.save(post);
    }
}
