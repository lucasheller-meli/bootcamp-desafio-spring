package com.challenge.services;

import com.challenge.dtos.CreatePostRequest;
import com.challenge.dtos.FollowedPostsResponse;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.entities.Seller;
import com.challenge.entities.User;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.PostRepository;
import com.challenge.sorting.post.PostSortOption;
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
    private final SellerService sellerService;
    private final FollowService followService;
    private static final Integer WEEKS_TO_SHOW = 2;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void create(CreatePostRequest createPostRequest) throws SellerNotFound {
        Seller seller = sellerService.findById(createPostRequest.getSellerId());
        Product product = productService.create(createPostRequest.getProduct());

        postRepository.save(Post.builder()
                .product(product)
                .seller(seller)
                .category(createPostRequest.getCategory())
                .price(createPostRequest.getPrice())
                .date(LocalDate.now())
                .build());
    }

    public FollowedPostsResponse followedPosts(Long userId, String order) throws UserNotFound {
        User user = userService.findById(userId);
        List<Long> followedIds = followService.followedSellerIds(userId);

        List<Post> posts;

        if (PostSortOption.DATE_ASCENDING.getOption().equals(order)) posts = postRepository.findAllBySellerIdInOrderByDateAsc(followedIds);
        else posts = postRepository.findAllBySellerIdInOrderByDateDesc(followedIds);

        return FollowedPostsResponse.builder()
                .userId(userId)
                .userName(user.getName())
                .posts(posts.stream().filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(WEEKS_TO_SHOW))).collect(Collectors.toList()))
                .build();
    }
}
