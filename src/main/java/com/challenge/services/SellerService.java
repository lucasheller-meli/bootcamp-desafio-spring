package com.challenge.services;

import com.challenge.dtos.CountFollowDTO;
import com.challenge.dtos.FollowDTO;
import com.challenge.entities.Seller;
import com.challenge.entities.User;
import com.challenge.exceptions.SellerNotFoundException;
import com.challenge.exceptions.UserNotFoundException;
import com.challenge.repositories.SellerRepository;
import com.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;


import java.util.HashMap;

import java.util.Map;


@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final UserService userService;


    public SellerService(SellerRepository sellerRepository, UserRepository userRepository, UserService userService) {
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void follow(Long userId, Long sellerId) throws UserNotFoundException, SellerNotFoundException {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(SellerNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        seller.AddFollowed(user.getUserId(),user.getUserName());
        user.AddFollower(seller.getSellerId(), seller.getSellerName());


        userRepository.save(user);
        sellerRepository.save(seller);
    }


    public CountFollowDTO countFollow(Long sellerId)  throws SellerNotFoundException{
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(SellerNotFoundException::new);

        return CountFollowDTO.builder()
                .name(seller.getSellerName())
                .id(seller.getSellerId())
                .countFollow(seller.getFollowed().size())
                .build();
    }

    public FollowDTO followerList(Long sellerId, String order) throws SellerNotFoundException {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(SellerNotFoundException::new);

        Map<Long, String> mapFollowers = new HashMap<>(seller.getFollowed());


        Map<Long, String> result = userService.sortByValue(mapFollowers, order);

        return FollowDTO.builder()
                .name(seller.getSellerName())
                .id(seller.getSellerId())
                .list(result)
                .build();
    }

    public void unfollow(Long userId, Long sellerId) throws UserNotFoundException, SellerNotFoundException{
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(SellerNotFoundException::new);
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        seller.removeFollowed(user.getUserId(),user.getUserName());
        user.removeFollower(seller.getSellerId(), seller.getSellerName());


        userRepository.save(user);
        sellerRepository.save(seller);
    }
}