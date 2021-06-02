package com.challenge.dtos;

import com.challenge.entities.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class FollowedResponse {
    private final Long userId;
    private final String userName;
    private final List<User> followed;
}
