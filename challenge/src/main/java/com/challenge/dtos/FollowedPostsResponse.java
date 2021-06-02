package com.challenge.dtos;

import com.challenge.entities.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FollowedPostsResponse {
    private Long userId;
    private String userName;
    private List<Post> posts;
}
