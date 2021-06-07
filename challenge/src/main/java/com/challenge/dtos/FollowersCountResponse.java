package com.challenge.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowersCountResponse {
    private final Long sellerId;
    private final String sellerName;
    private final Integer followersCount;
}
