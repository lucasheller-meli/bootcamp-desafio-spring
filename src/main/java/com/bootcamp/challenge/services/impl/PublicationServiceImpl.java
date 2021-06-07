package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.controllers.response.FollowedPublicationResponse;
import com.bootcamp.challenge.controllers.response.PublicationCountResponse;
import com.bootcamp.challenge.controllers.response.PublicationResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserResponse;
import com.bootcamp.challenge.entities.ProductEntity;
import com.bootcamp.challenge.entities.PublicationEntity;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.entities.UserType;
import com.bootcamp.challenge.exceptions.InvalidOperationException;
import com.bootcamp.challenge.exceptions.InvalidUserTypeForOperationException;
import com.bootcamp.challenge.repositories.PublicationRepository;
import com.bootcamp.challenge.services.FollowService;
import com.bootcamp.challenge.services.ProductService;
import com.bootcamp.challenge.services.PublicationService;
import com.bootcamp.challenge.services.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
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
        if (!publicationRequest.getHasPromo() && BigDecimal.ZERO.compareTo(publicationRequest.getDiscount()) < 0) {
            throw new InvalidOperationException("Não é possivel o desconto ser maior que 0 se a publicacao não está em promocao");
        }

        final UserEntity userEntity = userService.findById(publicationRequest.getUserId());
        verifyUserType(userEntity.getType());
        final ProductEntity productEntity = productService.findById(publicationRequest.getProductId());

        final PublicationEntity publicationEntity = convertRequestToPublicationEntity(publicationRequest, userEntity, productEntity);

        return Optional.of(publicationEntity)
                .map(publicationRepository::save)
                .map(PublicationEntity::getId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public PublicationCountResponse countPublication(Integer userId, Boolean hasPromo) {
        final UserEntity userEntity = userService.findById(userId);
        verifyUserType(userEntity.getType());
        final var totalPublication = publicationRepository.countAllByUser_IdAndHasPromo(userId, hasPromo);
        return PublicationCountResponse
                .builder()
                .publicationCount(totalPublication)
                .userId(userEntity.getId())
                .userName(userEntity.getName())
                .build();
    }

    @Override
    public List<PublicationResponse> findAll(Integer userId, Boolean hasPromo, int page, int pageSize) {
        final UserEntity userEntity = userService.findById(userId);
        verifyUserType(userEntity.getType());
        final PageRequest pageable = PageRequest.of(page, pageSize);
        return PublicationResponse.of(publicationRepository.findAllByUser_IdAndHasPromo(pageable, userId, hasPromo));
    }

    @Override
    public FollowedPublicationResponse findAllProductsFromFollowed(Integer userId, int page, int pageSize, String orderBy, Boolean hasPromo, Sort.Direction direction) {
        final UserEntity userEntity = userService.findById(userId);
        final UserFollowedResponse userFollowed = followService.listFollowed(userEntity.getId(), "asc");
        final List<Integer> idsFollowed = userFollowed.getFollowedUsers().stream().map(UserResponse::getId).collect(Collectors.toList());
        final PageRequest pageable = PageRequest.of(page, pageSize, Sort.by(direction, orderBy));


        final List<PublicationEntity> publicationEntities = publicationRepository.findAllByUser_IdInAndCreateDateIsGreaterThanEqualAndHasPromo(pageable, idsFollowed, LocalDateTime.now().minusWeeks(2), hasPromo);
        return FollowedPublicationResponse.of(userEntity.getId(), publicationEntities);
    }

    private PublicationEntity convertRequestToPublicationEntity(PublicationRequest publicationRequest,
                                                                UserEntity userEntity,
                                                                ProductEntity productEntity) {
        verifyUserType(userEntity.getType());

        return PublicationEntity.builder()
                .user(userEntity)
                .product(productEntity)
                .hasPromo(publicationRequest.getHasPromo())
                .discount(publicationRequest.getDiscount())
                .category(publicationRequest.getCategory())
                .price(publicationRequest.getPrice())
                .build();

    }

    private void verifyUserType(UserType userType) {
        if (!UserType.SELLER.equals(userType)) {
            throw new InvalidUserTypeForOperationException("O usuário do tipo " + userType + " nao pode criar publicacao.");
        }
    }


}
