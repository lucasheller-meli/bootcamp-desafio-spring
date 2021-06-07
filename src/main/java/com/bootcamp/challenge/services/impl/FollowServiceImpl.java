package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserResponse;
import com.bootcamp.challenge.entities.FollowEntity;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.entities.UserType;
import com.bootcamp.challenge.exceptions.FollowingException;
import com.bootcamp.challenge.exceptions.InvalidUserTypeForOperationException;
import com.bootcamp.challenge.repositories.FollowRepository;
import com.bootcamp.challenge.services.FollowService;
import com.bootcamp.challenge.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        verifyIfUsersAreDifferents(followedUser.getId(), followerUser.getId());
        verifyIfUsersFollowEachOther(followerUser.getId(), followedUser.getId());

        final FollowEntity followEntity = FollowEntity.builder().followed(followedUser).follower(followerUser).build();
        followRepository.save(followEntity);
    }

    @Override
    public UserCountFollowersResponse countFollowers(Integer userId) {
        final UserEntity userEntity = userService.findById(userId);
        validateUserType(userEntity, UserType.SELLER);
        final var countFollower = followRepository.countAllByFollowedIs(userEntity);
        return UserCountFollowersResponse.of(userEntity, countFollower);
    }

    @Override
    public UserFollowersResponse listFollowers(Integer userId, String direction) {
        final UserEntity userEntity = userService.findById(userId);
        validateUserType(userEntity, UserType.SELLER);
        List<FollowEntity> followEntity;
        if ("desc".equalsIgnoreCase(direction)) {
            followEntity = followRepository.findAllByFollowed_IdOrderByFollower_NameDesc(userEntity.getId());
        } else {
            followEntity = followRepository.findAllByFollowed_IdOrderByFollower_NameAsc(userEntity.getId());
        }
        final List<UserResponse> userResponses = new ArrayList<>();
        followEntity.forEach(f -> userResponses.add(UserResponse.builder()
                .id(f.getFollower().getId())
                .name(f.getFollower().getName())
                .build()));
        return UserFollowersResponse
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .followersUsers(userResponses)
                .build();
    }

    @Override
    public UserFollowedResponse listFollowed(Integer userId, String orderBy) {
        final UserEntity userEntity = userService.findById(userId);
        List<FollowEntity> followEntity;
        if ("desc".equalsIgnoreCase(orderBy)) {
            followEntity = followRepository.findAllByFollower_IdOrderByFollowed_NameDesc(userEntity.getId());
        } else {
            followEntity = followRepository.findAllByFollower_IdOrderByFollowed_NameAsc(userEntity.getId());
        }
        return UserFollowedResponse
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .followedUsers(convertToListUserResponse(followEntity))
                .build();
    }


    @Override
    public void unfollowSeller(Integer userUnfollowing, Integer userUnfollowed) {
        final UserEntity unfollowedUser = userService.findById(userUnfollowed);
        validateUserType(unfollowedUser, UserType.SELLER);
        final FollowEntity followEntity = verifyFollowByIds(userUnfollowing, userUnfollowed);
        followRepository.delete(followEntity);
    }

    private FollowEntity verifyFollowByIds(Integer followerId, Integer followedId) {
        followRepository.findFirstByFollower_IdAndFollowed_Id(followerId, followedId);
        Optional<FollowEntity> followEntity = followRepository.findFirstByFollower_IdAndFollowed_Id(followerId, followedId);
        if (followEntity.isPresent()) {
            return followEntity.get();
        } else {
            throw new FollowingException("Usuario nao segue o vendedor.");
        }

    }

    private void validateUserType(UserEntity userEntity, UserType userType) {
        if (!userType.equals(userEntity.getType())) {
            throw new InvalidUserTypeForOperationException("Usuário nao pode ser do tipo " + userEntity.getType());
        }
    }

    private void verifyIfUsersFollowEachOther(Integer followerId, Integer followedId) {
        if (followRepository.existsByFollower_IdAndFollowed_Id(followerId, followedId)) {
            throw new FollowingException("Usuários ja se seguem.");
        }

    }

    private void verifyIfUsersAreDifferents(Integer user1, Integer user2) {
        if (user1 == user2) {
            throw new FollowingException("Usuários iguais.");
        }
    }

    private List<UserResponse> convertToListUserResponse(List<FollowEntity> followEntities) {
        return followEntities.stream().map(this::convertToUserResponse).collect(Collectors.toList());
    }

    private UserResponse convertToUserResponse(FollowEntity followEntity) {
        return UserResponse.builder()
                .id(followEntity.getFollowed().getId())
                .name(followEntity.getFollowed().getName())
                .build();
    }



}
