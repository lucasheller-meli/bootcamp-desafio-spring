package com.challenge.service;

import com.challenge.entity.Retailer;
import com.challenge.entity.User;
import com.challenge.entity.UserRetailer;
import com.challenge.exception.*;
import com.challenge.model.followers.FollowedDTO;
import com.challenge.model.followers.FollowersDTO;
import com.challenge.model.user.UserDTO;
import com.challenge.repository.RetailerRepository;
import com.challenge.repository.UserRepository;
import com.challenge.repository.UserRetailerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final RetailerRepository retailerRepository;

    private final UserRepository userRepository;

    private final UserRetailerRepository urRepository;

    public UserService(RetailerRepository retailerRepository, UserRepository userRepository, UserRetailerRepository urRepository) {
        this.retailerRepository = retailerRepository;
        this.userRepository = userRepository;
        this.urRepository = urRepository;
    }

    public void follow(Integer userId, Integer retailerId) throws UserNotFound, RetailerNotFound {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFound(userId));
        Retailer retailer = retailerRepository.findById(retailerId).orElseThrow(() -> new RetailerNotFound(retailerId));
        urRepository.save(UserRetailer.builder().follower(user).retailer(retailer).build());
    }

    public void unfollow(Integer userId, Integer retailerId) throws UnfollowException {
        UserRetailer follower = urRepository.findByFollower_IdAndRetailer_Id(userId, retailerId).orElseThrow(() -> new
                UnfollowException(userId,retailerId));
        urRepository.delete(follower);
    }

    public Integer countFollowers(Integer retailerId) {
        return urRepository.countUserRetailerByRetailer_Id(retailerId);
    }

    public FollowersDTO whoFollowMe(Integer retailerId, String order) throws FollowersNotFound {
        List<UserRetailer> userRetailers;
        if (order != null) {
            userRetailers = order.endsWith("desc") ? urRepository.findAllByRetailer_IdOrderByFollower_NameDesc(retailerId)
                    : urRepository.findAllByRetailer_IdOrderByFollower_NameAsc(retailerId);
        } else {
            userRetailers = urRepository.findAllByRetailer_Id(retailerId).orElseThrow(() -> new FollowersNotFound(retailerId));
        }

        return this.followersDTO(userRetailers);
    }

    private FollowersDTO followersDTO(List<UserRetailer> userRetailers) {
        Retailer retailer = userRetailers.get(0).getRetailer();
        FollowersDTO followers = FollowersDTO.builder().userId(retailer.getId()).userName(retailer.getName()).followers(new ArrayList<>()).build();
        for(UserRetailer follower : userRetailers) {
            UserDTO userDTO = UserDTO.builder().userName(follower.getFollower().getName()).userId(follower.getFollower().getId().toString()).build();
            followers.getFollowers().add(userDTO);
        }
        return followers;
    }

    public FollowedDTO whoAmIFollowing(Integer userId) throws FollowedException {
        List<UserRetailer> userRetailers = urRepository.findAllByFollower_Id(userId).orElseThrow(() -> new FollowedException(userId));
        return this.followedDTO(userRetailers);
    }

    private FollowedDTO followedDTO(List<UserRetailer> userRetailers) {
        User user = userRetailers.get(0).getFollower();
        FollowedDTO followers = FollowedDTO.builder().userId(user.getId()).userName(user.getName()).followed(new ArrayList<>()).build();
        for(UserRetailer follower : userRetailers) {
            followers.getFollowed().add(UserDTO.builder().userId(follower.getRetailer().getId().toString()).userName(follower.getRetailer().getName()).build());
        }
        return followers;
    }
}
