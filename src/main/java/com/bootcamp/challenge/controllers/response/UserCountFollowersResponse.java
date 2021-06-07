package com.bootcamp.challenge.controllers.response;

import com.bootcamp.challenge.entities.UserEntity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserCountFollowersResponse extends UserResponse {

    private Long followersCount;

    public static UserCountFollowersResponse of(UserEntity userEntity, Long followersCount) {
        return UserCountFollowersResponse
                .builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .followersCount(followersCount)
                .build();
    }
}
