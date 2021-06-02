package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.controllers.response.FollowedPublicationResponse;
import com.bootcamp.challenge.entities.PublicationEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PublicationService {

    Integer createPublication(PublicationRequest publicationRequest);

    List<PublicationEntity> findAll(int page, int pageSize, String orderBy, Sort.Direction direction);

    FollowedPublicationResponse findAllProductsFromFollowed(Integer usedId, int page, int pageSize, String orderBy, Sort.Direction direction);
}
