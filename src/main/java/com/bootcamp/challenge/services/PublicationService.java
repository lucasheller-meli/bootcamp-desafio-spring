package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.controllers.response.FollowedPublicationResponse;
import com.bootcamp.challenge.controllers.response.PublicationCountResponse;
import com.bootcamp.challenge.controllers.response.PublicationResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface PublicationService {

    Integer createPublication(PublicationRequest publicationRequest);

    PublicationCountResponse countPublication(Integer userId, Boolean hasPromo);

    List<PublicationResponse> findAll(Integer userId, Boolean hasPromo, int page, int pageSize);

    FollowedPublicationResponse findAllProductsFromFollowed(Integer usedId, int page, int pageSize, String orderBy, Boolean hasPromo, Sort.Direction direction);
}
