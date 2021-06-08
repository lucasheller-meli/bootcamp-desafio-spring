package com.challenge.repository;

import com.challenge.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Integer>, JpaSpecificationExecutor<Publication> {

    Optional<List<Publication>> findAllByRetailer_IdAndHasPromo(Integer retailerId, boolean hasPromo);

}
