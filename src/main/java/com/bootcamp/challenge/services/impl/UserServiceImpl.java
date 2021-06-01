package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.request.UserCreateRequest;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.exceptions.NotFoundException;
import com.bootcamp.challenge.repositories.UserRepository;
import com.bootcamp.challenge.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Integer createUser(UserCreateRequest userCreateRequest){
        final UserEntity userEntity = convertRequestToEntity(userCreateRequest);
        return Optional.of(userEntity)
                .map(userRepository::save)
                .map(UserEntity::getId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User de id "+id+" nao encontrado"));
    }

    private UserEntity convertRequestToEntity(UserCreateRequest userCreateRequest){
        return UserEntity.builder()
                .name(userCreateRequest.getName())
                .type(userCreateRequest.getType())
                .build();
    }
}
