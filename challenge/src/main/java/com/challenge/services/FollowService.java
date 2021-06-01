package com.challenge.services;

import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    public void follow(Long followerId, Long followedId) throws UserNotFound, SelfFollow, AlreadyFollowing {
        if (followerId.equals(followedId)) throw new SelfFollow(followerId);

        boolean isAlreadyFollowing = followRepository.findByFollowerIdAndFollowedId(followerId, followedId).isPresent();
        if (isAlreadyFollowing) throw new AlreadyFollowing(followerId, followedId);

        User follower = userService.findById(followerId);
        User followed = userService.findById(followedId);
        followRepository.save(Follow.builder().follower(follower).followed(followed).build());
    }

    public List<Follow> followers(Long userId) {
        return followRepository.findAllByFollowedId(userId);
    }
}
