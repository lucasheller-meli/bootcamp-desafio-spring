package com.challenge.services;

import com.challenge.dtos.FollowDTO;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFoundException;
import com.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public FollowDTO followedList(Long userId, String order)  throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Map<Long, String> mapFollowing = new HashMap<>(user.getFollower());


        Map<Long, String> result = sortByValue(mapFollowing, order);

        return FollowDTO.builder().name(user.getUserName())
                .id(user.getUserId())
                .list(result)
                .build();
    }

    public <Long, String extends Comparable<? super String>> Map<Long, String> sortByValue(Map<Long, String> map, String order){
        List<Map.Entry<Long, String>> list = new ArrayList<>(map.entrySet());
        Map<Long, String> result;

        if("name_desc".equals(order)) {
            result = new TreeMap<>(Collections.reverseOrder());
            result.putAll(map);
        } else{
            list.sort(Map.Entry.comparingByValue());

            result = new LinkedHashMap<>();
            for(Map.Entry<Long, String> entry : list){
                result.put(entry.getKey(), entry.getValue());
            }
        }

        return result;
    }

}