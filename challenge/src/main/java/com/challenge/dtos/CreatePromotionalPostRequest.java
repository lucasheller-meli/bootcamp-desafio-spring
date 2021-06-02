package com.challenge.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CreatePromotionalPostRequest extends CreatePostRequest {
    @NotNull
    private Boolean hasPromotion;
    @NotNull
    @Positive
    private Double discount;
}
