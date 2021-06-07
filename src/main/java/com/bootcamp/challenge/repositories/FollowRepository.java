package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.FollowEntity;
import com.bootcamp.challenge.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowEntity, Integer> {

    Long countAllByFollowedIs(UserEntity followed);

    List<FollowEntity> findAllByFollower_IdOrderByFollowed_NameAsc(Integer follower_id);
    List<FollowEntity> findAllByFollower_IdOrderByFollowed_NameDesc(Integer follower_id);

    List<FollowEntity> findAllByFollowed_IdOrderByFollower_NameAsc(Integer followed_id);
    List<FollowEntity> findAllByFollowed_IdOrderByFollower_NameDesc(Integer followed_id);

    Optional<FollowEntity> findFirstByFollower_IdAndFollowed_Id(Integer followerId, Integer followedId);

    boolean existsByFollower_IdAndFollowed_Id(Integer followerId, Integer followedId);

}
