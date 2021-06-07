package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;

public interface FollowService {

    void followUser(Integer userFollowerId, Integer userFollowedId);

    UserCountFollowersResponse countFollowers(Integer userId);

    UserFollowersResponse listFollowers(Integer userId, String orderBy);

    UserFollowedResponse listFollowed(Integer userId, String orderBy);

    void unfollowSeller(Integer userUnfollowing, Integer userUnfollowed);

}
