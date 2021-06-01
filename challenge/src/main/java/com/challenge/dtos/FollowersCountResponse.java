package com.challenge.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowersCountResponse {
    private Long userId;
    private String userName;
    private Integer followersCount;
}
