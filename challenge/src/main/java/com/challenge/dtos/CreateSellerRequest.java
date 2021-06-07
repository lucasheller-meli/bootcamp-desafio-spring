package com.challenge.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateSellerRequest {
    @NotNull
    @Size(min = 2, max = 200)
    private String name;
}
