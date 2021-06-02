package com.challenge.services;

import com.challenge.dtos.CreatePromotionalPostRequest;
import com.challenge.entities.Product;
import com.challenge.entities.PromotionalPost;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.PromotionalPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionalPostService {
    private final PromotionalPostRepository promotionalPostRepository;
    private final ProductService productService;
    private final UserService userService;

    public List<PromotionalPost> findAll() {
        return promotionalPostRepository.findAll();
    }

    public void create(CreatePromotionalPostRequest createPostRequest) throws UserNotFound {
        Product product = productService.create(createPostRequest.getProduct());
        User user = userService.findById(createPostRequest.getUserId());

        save(PromotionalPost.builder()
                .product(product)
                .user(user)
                .category(createPostRequest.getCategory())
                .price(createPostRequest.getPrice())
                .date(LocalDate.now())
                .hasPromotion(createPostRequest.getHasPromotion())
                .discount(createPostRequest.getDiscount())
                .build());
    }

    private void save(PromotionalPost post) {
        promotionalPostRepository.save(post);
    }
}
