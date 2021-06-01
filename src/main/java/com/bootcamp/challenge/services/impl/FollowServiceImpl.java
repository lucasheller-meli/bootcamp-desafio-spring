package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserResponse;
import com.bootcamp.challenge.entities.FollowEntity;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.entities.UserType;
import com.bootcamp.challenge.exceptions.InvalidTypeToFollowException;
import com.bootcamp.challenge.repositories.FollowRepository;
import com.bootcamp.challenge.services.FollowService;
import com.bootcamp.challenge.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository followRepository;
    private final UserService userService;

    public FollowServiceImpl(FollowRepository followRepository, UserService userService) {
        this.followRepository = followRepository;
        this.userService = userService;
    }

    @Override
    public void followUser(Integer userFollowerId, Integer userFollowedId) {
    final UserEntity followerUser = userService.findById(userFollowerId);
    final UserEntity followedUser = userService.findById(userFollowedId);

        validateUserType(followedUser, UserType.SELLER);

    final FollowEntity followEntity = FollowEntity.builder().followed(followedUser).follower(followerUser).build();
    followRepository.save(followEntity);
    }

    @Override
    public UserCountFollowersResponse countFollowers(Integer userId) {
        final UserEntity userEntity = userService.findById(userId);
        validateUserType(userEntity, UserType.SELLER);
        final var countFollower = followRepository.countAllByFollowedIs(userEntity);
        return UserCountFollowersResponse.of(userEntity,countFollower);
    }

    @Override
    public UserFollowersResponse listFollowers(Integer userId) {
        final UserEntity userEntity = userService.findById(userId);
        validateUserType(userEntity, UserType.SELLER);
        final List<FollowEntity> followEntity = followRepository.findAllByFollowedIs(userEntity);

        final List<UserResponse> userResponses = new ArrayList<>();
                followEntity.forEach(f -> userResponses.add(UserResponse.builder()
                        .id(f.getFollower().getId())
                        .name(f.getFollower().getName())
                        .build()));
        return UserFollowersResponse
                .builder()
                .id(userEntity.getId())
                .followersUsers(userResponses)
                .build();
    }


    private void validateUserType(UserEntity userEntity, UserType userType){
         if(!userType.equals(userEntity.getType())){
             throw new InvalidTypeToFollowException("Usuário nao pode ser do tipo "+userEntity.getType());
         }
    }
}
