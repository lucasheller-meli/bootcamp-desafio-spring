package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
