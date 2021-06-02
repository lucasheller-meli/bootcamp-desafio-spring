package com.challenge.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowersCountResponse {
    private final Long userId;
    private final String userName;
    private final Integer followersCount;
}
