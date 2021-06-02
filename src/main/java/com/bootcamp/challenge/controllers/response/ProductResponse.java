package com.bootcamp.challenge.controllers.response;

import com.bootcamp.challenge.entities.ProductEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Integer id;
    private String name;
    private String type;
    private String brand;
    private String color;
    private String notes;

    public static ProductResponse of(ProductEntity productEntity) {
        return ProductResponse
                .builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .type(productEntity.getType())
                .brand(productEntity.getBrand())
                .color(productEntity.getColor())
                .notes(productEntity.getNotes())
                .build();
    }


}
