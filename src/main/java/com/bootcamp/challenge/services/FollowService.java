package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.response.UserCountFollowersResponse;
import com.bootcamp.challenge.controllers.response.UserFollowedResponse;
import com.bootcamp.challenge.controllers.response.UserFollowersResponse;

public interface FollowService {

    void followUser(Integer userFollowerId, Integer userFollowedId);

    UserCountFollowersResponse countFollowers(Integer userId);

    UserFollowersResponse listFollowers(Integer userId);

    UserFollowedResponse listFollowed(Integer userId);



}
