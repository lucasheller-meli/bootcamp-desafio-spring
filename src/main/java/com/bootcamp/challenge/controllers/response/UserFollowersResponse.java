package com.bootcamp.challenge.controllers.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class UserFollowersResponse extends UserResponse {

    List<UserResponse> followersUsers;

}
