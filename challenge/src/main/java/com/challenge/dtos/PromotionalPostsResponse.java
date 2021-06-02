package com.challenge.dtos;

import com.challenge.entities.PromotionalPost;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class PromotionalPostsResponse {
    private final Long userId;
    private final String userName;
    private final List<PromotionalPost> promotionalPosts;
}
