package com.challenge.dtos;

import com.challenge.entities.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FollowedPostsResponse {
    private final Long userId;
    private final String userName;
    private final List<Post> posts;
}
