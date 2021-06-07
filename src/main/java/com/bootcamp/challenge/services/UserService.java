package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.request.UserCreateRequest;
import com.bootcamp.challenge.entities.UserEntity;

public interface UserService {
    Integer createUser(UserCreateRequest userRequest);

    UserEntity findById(Integer id);
}
