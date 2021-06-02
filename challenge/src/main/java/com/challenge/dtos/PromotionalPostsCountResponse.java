package com.challenge.dtos;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PromotionalPostsCountResponse {
    private final Long userId;
    private final String userName;
    private final Integer promotionalPostsCount;
}
