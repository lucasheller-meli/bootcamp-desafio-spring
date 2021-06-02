package com.challenge.services;

import com.challenge.dtos.FollowedResponse;
import com.challenge.dtos.FollowersCountResponse;
import com.challenge.dtos.FollowersResponse;
import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;

    public void follow(Long followerId, Long followedId) throws UserNotFound, SelfFollow, AlreadyFollowing {
        if (followerId.equals(followedId)) throw new SelfFollow(followerId);

        boolean isAlreadyFollowing = findByFollowerIdAndFollowedId(followerId, followedId).isPresent();
        if (isAlreadyFollowing) throw new AlreadyFollowing(followerId, followedId);

        User follower = userService.findById(followerId);
        User followed = userService.findById(followedId);
        followRepository.save(Follow.builder().follower(follower).followed(followed).build());
    }

    public FollowersCountResponse followersCount(Long userId) throws UserNotFound {
        User user = userService.findById(userId);

        return FollowersCountResponse.builder()
                .userId(userId)
                .userName(user.getName())
                .followersCount(findAllByFollowedId(userId).size())
                .build();
    }

    public FollowersResponse followers(Long userId) throws UserNotFound {
        User user = userService.findById(userId);

        return FollowersResponse.builder()
                .userId(userId)
                .userName(user.getName())
                .followers(findAllByFollowedId(userId).stream().map(Follow::getFollower).collect(Collectors.toList()))
                .build();
    }

    public FollowedResponse followed(Long userId) throws UserNotFound {
        User user = userService.findById(userId);

        return FollowedResponse.builder()
                .userId(userId)
                .userName(user.getName())
                .followed(findAllByFollowerId(userId).stream().map(Follow::getFollowed).collect(Collectors.toList()))
                .build();
    }

    public void unfollow(Long followerId, Long followedId) {
        findByFollowerIdAndFollowedId(followerId, followedId).ifPresent(followRepository::delete);
    }

    private Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId) {
        return followRepository.findByFollowerIdAndFollowedId(followerId, followedId);
    }
    private List<Follow> findAllByFollowedId(Long userId) {
        return followRepository.findAllByFollowedId(userId);
    }
    private List<Follow> findAllByFollowerId(Long userId) {
        return followRepository.findAllByFollowerId(userId);
    }
}
