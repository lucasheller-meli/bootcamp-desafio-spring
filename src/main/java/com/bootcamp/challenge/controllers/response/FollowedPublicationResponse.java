package com.bootcamp.challenge.controllers.response;

import com.bootcamp.challenge.entities.PublicationEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FollowedPublicationResponse {

    private Integer userId;
    private List<PublicationResponse> publicationsResponses;

    public static FollowedPublicationResponse of(Integer userId, List<PublicationEntity> publicationEntities){
        return FollowedPublicationResponse
                .builder()
                .userId(userId)
                .publicationsResponses(PublicationResponse.of(publicationEntities))
                .build();
    }
}
