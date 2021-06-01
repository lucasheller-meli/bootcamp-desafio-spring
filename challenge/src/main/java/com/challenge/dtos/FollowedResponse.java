package com.challenge.dtos;

import com.challenge.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FollowedResponse {
    private Long userId;
    private String userName;
    private List<User> followed;
}
