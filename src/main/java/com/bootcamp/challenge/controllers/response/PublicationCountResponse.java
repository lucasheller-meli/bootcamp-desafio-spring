package com.bootcamp.challenge.controllers.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublicationCountResponse {

    private Integer userId;
    private Long publicationCount;
    private String userName;

}
