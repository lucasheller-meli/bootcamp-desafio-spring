package com.challenge.services;

import com.challenge.dtos.FollowedResponse;
import com.challenge.dtos.FollowersCountResponse;
import com.challenge.dtos.FollowersResponse;
import com.challenge.entities.Follow;
import com.challenge.entities.Seller;
import com.challenge.entities.User;
import com.challenge.exceptions.AlreadyFollowing;
import com.challenge.exceptions.SelfFollow;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.FollowRepository;
import com.challenge.sorting.user.FollowListUserSorterFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private final SellerService sellerService;

    public void follow(Long followerId, Long followedId) throws UserNotFound, SellerNotFound, SelfFollow, AlreadyFollowing {
        User follower = userService.findById(followerId);
        Seller followed = sellerService.findById(followedId);

        if (followerId.equals(followedId)) throw new SelfFollow(followerId);

        boolean isAlreadyFollowing = findByFollowerIdAndFollowedId(followerId, followedId).isPresent();
        if (isAlreadyFollowing) throw new AlreadyFollowing(followerId, followedId);

        followRepository.save(Follow.builder().follower(follower).followed(followed).build());
    }

    public FollowersCountResponse followersCount(Long sellerId) throws SellerNotFound {
        Seller seller = sellerService.findById(sellerId);

        return FollowersCountResponse.builder()
                .sellerId(sellerId)
                .sellerName(seller.getName())
                .followersCount(findAllByFollowedId(sellerId).size())
                .build();
    }

    public FollowersResponse followers(Long sellerId, String order) throws SellerNotFound {
        Seller seller = sellerService.findById(sellerId);
        List<User> followers = FollowListUserSorterFactory.create(order).sort(followersStream(sellerId));

        return FollowersResponse.builder()
                .sellerId(sellerId)
                .sellerName(seller.getName())
                .followers(followers)
                .build();
    }

    public FollowedResponse followed(Long userId, String order) throws UserNotFound {
        User user = userService.findById(userId);
        List<Seller> followed = FollowListUserSorterFactory.create(order).sort(followedStream(userId));

        return FollowedResponse.builder()
                .userId(userId)
                .userName(user.getName())
                .followed(followed)
                .build();
    }

    public void unfollow(Long followerId, Long followedId) {
        findByFollowerIdAndFollowedId(followerId, followedId).ifPresent(followRepository::delete);
    }

    public List<Long> followedSellerIds(Long userId) {
        return followedStream(userId).map(User::getId).collect(Collectors.toList());
    }

    private Stream<User> followersStream(Long sellerId) {
        return findAllByFollowedId(sellerId).stream().map(Follow::getFollower);
    }
    private Stream<Seller> followedStream(Long userId) {
        return findAllByFollowerId(userId).stream().map(Follow::getFollowed);
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
