package com.challenge.repository;

import com.challenge.entity.UserRetailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRetailerRepository extends JpaRepository<UserRetailer, Integer>, JpaSpecificationExecutor<UserRetailer> {

    Integer countUserRetailerByRetailer_Id(Integer retailerId);

    Optional<List<UserRetailer>> findAllByRetailer_Id(Integer retailerId);

    Optional<List<UserRetailer>> findAllByFollower_Id(Integer followerId);

    Optional<UserRetailer> findByFollower_IdAndRetailer_Id(Integer followerId, Integer retailerId);

    Optional<List<UserRetailer>> findByFollower_Id(Integer followerId);

    List<UserRetailer> findAllByRetailer_IdOrderByFollower_NameDesc(Integer follower_id);

    List<UserRetailer> findAllByRetailer_IdOrderByFollower_NameAsc(Integer followed_id);



}
