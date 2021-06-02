package com.challenge.services;

import com.challenge.dtos.CreatePostRequest;
import com.challenge.dtos.FollowedPostsResponse;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ProductService productService;
    private final UserService userService;
    private final FollowService followService;
    private static final Integer WEEKS_TO_SHOW = 2;

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
                .date(LocalDate.now())
                .build());
    }

    public FollowedPostsResponse followedPosts(Long userId) throws UserNotFound {
        List<Long> followedIds = followService.followed(userId).getFollowed().stream().map(User::getId).collect(Collectors.toList());

        List<Post> posts = postRepository.findAllByUserIdInOrderByDateDesc(followedIds)
                .stream()
                .filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(WEEKS_TO_SHOW)))
                .collect(Collectors.toList());

        return FollowedPostsResponse.builder()
                .userId(userId)
                .posts(posts)
                .build();
    }

    private void save(Post post) {
        postRepository.save(post);
    }
}
