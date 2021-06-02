package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.controllers.response.FollowedPublicationResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserResponse;
import com.bootcamp.challenge.entities.ProductEntity;
import com.bootcamp.challenge.entities.PublicationEntity;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.entities.UserType;
import com.bootcamp.challenge.exceptions.InvalidUserTypeForOperationException;
import com.bootcamp.challenge.repositories.PublicationRepository;
import com.bootcamp.challenge.services.FollowService;
import com.bootcamp.challenge.services.ProductService;
import com.bootcamp.challenge.services.PublicationService;
import com.bootcamp.challenge.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserService userService;
    private final ProductService productService;
    private final FollowService followService;

    public PublicationServiceImpl(PublicationRepository publicationRepository, UserService userService, ProductService productService, FollowService followService) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
        this.productService = productService;
        this.followService = followService;
    }

    @Override
    public Integer createPublication(PublicationRequest publicationRequest) {
        final UserEntity userEntity = userService.findById(publicationRequest.getUserId());
        final ProductEntity productEntity = productService.findById(publicationRequest.getProductId());

        final PublicationEntity publicationEntity = convertRequestToPublicationEntity(publicationRequest, userEntity, productEntity);

        return Optional.of(publicationEntity)
                .map(publicationRepository::save)
                .map(PublicationEntity::getId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<PublicationEntity> findAll(int page, int pageSize, String orderBy, Sort.Direction direction) {
        final PageRequest pageable = PageRequest.of(page, pageSize, Sort.by(direction, orderBy));
        publicationRepository.findAllByCreateDateIsGreaterThanEqual(pageable, LocalDateTime.now().minusWeeks(2));
        return Collections.EMPTY_LIST;
    }

    @Override
    public FollowedPublicationResponse findAllProductsFromFollowed(Integer userId, int page, int pageSize, String orderBy, Sort.Direction direction) {
        final UserEntity userEntity = userService.findById(userId);
        final UserFollowedResponse userFollowed = followService.listFollowed(userEntity.getId());
        List<Integer> idsFolloweds = userFollowed.getFollowedUsers().stream().map(UserResponse::getId).collect(Collectors.toList());
        final PageRequest pageable = PageRequest.of(page, pageSize, Sort.by(direction, orderBy));


        final List<PublicationEntity> publicationEntities = publicationRepository.findAllByUser_IdInAndCreateDateIsGreaterThanEqual(pageable, idsFolloweds, LocalDateTime.now().minusWeeks(2));
        return FollowedPublicationResponse.of(userEntity.getId(), publicationEntities);
    }

    private PublicationEntity convertRequestToPublicationEntity(PublicationRequest publicationRequest,
                                                                UserEntity userEntity,
                                                                ProductEntity productEntity) {
        if (!UserType.SELLER.equals(userEntity.getType())) {
            throw new InvalidUserTypeForOperationException("O usuário do tipo " + userEntity.getType() + " nao pode criar publicacao.");
        }

        return PublicationEntity.builder()
                .user(userEntity)
                .product(productEntity)
                .category(publicationRequest.getCategory())
                .price(publicationRequest.getPrice())
                .build();

    }
}
