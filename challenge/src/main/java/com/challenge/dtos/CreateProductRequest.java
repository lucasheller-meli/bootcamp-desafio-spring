package com.challenge.dtos;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateProductRequest {
    @NotNull
    @Size(min = 1, max = 200)
    private String name;
    @NotNull
    @Size(min = 1, max = 200)
    private String type;
    @NotNull
    @Size(min = 1, max = 200)
    private String brand;
    @NotNull
    @Size(min = 1, max = 200)
    private String color;
    @NotNull
    @Size(min = 1, max = 500)
    private String note;
}
