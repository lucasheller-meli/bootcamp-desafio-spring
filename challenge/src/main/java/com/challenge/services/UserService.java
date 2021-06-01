package com.challenge.services;

import com.challenge.dtos.CreateUserRequest;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws UserNotFound {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFound(id));
    }

    public User save(CreateUserRequest createUserRequest) {
        return userRepository.save(User.builder().name(createUserRequest.getName()).build());
    }
}
