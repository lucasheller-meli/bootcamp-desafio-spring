package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.PublicationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PublicationRepository extends JpaRepository<PublicationEntity, Integer> {

    Long countAllByUser_IdAndHasPromo(Integer userId, Boolean hasPromo);

    List<PublicationEntity> findAllByUser_IdAndHasPromo(Pageable pageable, Integer userId, Boolean hasPromo);

    List<PublicationEntity> findAllByUser_IdInAndCreateDateIsGreaterThanEqualAndHasPromo(Pageable pageable, List<Integer> userId, LocalDateTime createDate, Boolean hasPromo);

}
