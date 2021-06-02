package com.bootcamp.challenge.controllers.response;

import com.bootcamp.challenge.entities.PublicationEntity;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@Builder
public class PublicationResponse {

    private Integer id;
    private Integer userId;
    private LocalDateTime createDate;
    private ProductResponse productResponse;
    private Integer category;
    private BigDecimal price;

    public static List<PublicationResponse> of(List<PublicationEntity> publications) {
        return publications.stream().map(PublicationResponse::of).collect(Collectors.toList());
    }

    public static PublicationResponse of(PublicationEntity publicationEntity) {
        return PublicationResponse
                .builder()
                .id(publicationEntity.getId())
                .userId(publicationEntity.getUser().getId())
                .createDate(publicationEntity.getCreateDate())
                .productResponse(ProductResponse.of(publicationEntity.getProduct()))
                .category(publicationEntity.getCategory())
                .price(publicationEntity.getPrice())
                .build();
    }

}
