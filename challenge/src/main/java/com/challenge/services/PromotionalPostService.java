package com.challenge.services;

import com.challenge.dtos.CreatePromotionalPostRequest;
import com.challenge.dtos.PromotionalPostsCountResponse;
import com.challenge.dtos.PromotionalPostsResponse;
import com.challenge.entities.Product;
import com.challenge.entities.PromotionalPost;
import com.challenge.entities.Seller;
import com.challenge.exceptions.SellerNotFound;
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
    private final SellerService sellerService;

    public List<PromotionalPost> findAll() {
        return promotionalPostRepository.findAll();
    }

    public void create(CreatePromotionalPostRequest createPostRequest) throws SellerNotFound {
        Seller seller = sellerService.findById(createPostRequest.getSellerId());
        Product product = productService.create(createPostRequest.getProduct());

        promotionalPostRepository.save(PromotionalPost.builder()
                .product(product)
                .seller(seller)
                .category(createPostRequest.getCategory())
                .price(createPostRequest.getPrice())
                .date(LocalDate.now())
                .hasPromotion(createPostRequest.getHasPromotion())
                .discount(createPostRequest.getDiscount())
                .build());
    }

    public PromotionalPostsCountResponse promotionalPostsCount(Long sellerId) throws SellerNotFound {
        Seller seller = sellerService.findById(sellerId);

        return PromotionalPostsCountResponse.builder()
                .sellerId(sellerId)
                .sellerName(seller.getName())
                .promotionalPostsCount(findAllBySellerId(sellerId).size())
                .build();
    }

    public PromotionalPostsResponse promotionalPosts(Long sellerId) throws SellerNotFound {
        Seller seller = sellerService.findById(sellerId);

        return PromotionalPostsResponse.builder()
                .sellerId(sellerId)
                .sellerName(seller.getName())
                .promotionalPosts(findAllBySellerId(sellerId))
                .build();
    }

    private List<PromotionalPost> findAllBySellerId(Long sellerId) {
        return promotionalPostRepository.findAllBySellerId(sellerId);
    }
}
