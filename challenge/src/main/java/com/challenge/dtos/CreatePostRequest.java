package com.challenge.dtos;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CreatePostRequest {
    @Valid
    @NotNull
    private CreateProductRequest product;
    @NotNull
    @Positive
    private Long sellerId;
    @NotNull
    @Positive
    private Integer category;
    @NotNull
    @Positive
    private Double price;
}
