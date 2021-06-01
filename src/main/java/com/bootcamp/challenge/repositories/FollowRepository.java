package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.FollowEntity;
import com.bootcamp.challenge.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {

    Long countAllByFollowedIs(UserEntity followed);

    List<FollowEntity> findAllByFollowedIs(UserEntity followed);

}
