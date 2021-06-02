package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.PublicationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Integer> {

    List<PublicationEntity> findAllByCreateDateIsGreaterThanEqual(Pageable pageable, LocalDateTime createDate);

    List<PublicationEntity> findAllByUser_IdInAndCreateDateIsGreaterThanEqual(Pageable pageable, List<Integer> userId, LocalDateTime createDate);

}
