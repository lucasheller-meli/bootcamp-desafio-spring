package com.bootcamp.challenge.controllers.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class UserFollowedResponse extends UserResponse{
    List<UserResponse> followedUsers;
}
