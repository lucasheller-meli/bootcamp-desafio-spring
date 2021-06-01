package com.challenge.services;

import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.entities.Seller;
import com.challenge.repositories.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final UserService userService;
    private final SellerService sellerService;

    public void follow(Long followerId, Long followedId) {
        User follower = userService.findById(followerId).orElseThrow();
        Seller followed = sellerService.findById(followedId).orElseThrow();
        followRepository.save(Follow.builder().follower(follower).followed(followed).build());
    }

    public List<Follow> follows() {
        return followRepository.findAll();
    }
}