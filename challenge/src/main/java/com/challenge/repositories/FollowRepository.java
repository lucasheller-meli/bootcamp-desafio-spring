package com.challenge.repositories;

import com.challenge.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowedId(Long id);
    List<Follow> findAllByFollowerId(Long id);
    Optional<Follow> findByFollowerIdAndFollowedId(Long followerId, Long followedId);
}
