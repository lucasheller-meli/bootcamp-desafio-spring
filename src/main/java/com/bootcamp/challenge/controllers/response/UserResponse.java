package com.bootcamp.challenge.controllers.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserResponse {

    private Integer id;
    private String name;

}
