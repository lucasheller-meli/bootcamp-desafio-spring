package com.challenge.services;

import com.challenge.dtos.CreateUserDTO;
import com.challenge.entities.User;
import com.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(CreateUserDTO createUserDTO) {
        return userRepository.save(User.builder().name(createUserDTO.getName()).build());
    }
}
