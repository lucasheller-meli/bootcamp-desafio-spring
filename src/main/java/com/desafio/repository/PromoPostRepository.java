package com.desafio.repository;

import com.desafio.entities.Posters;
import com.desafio.entities.PromoPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PromoPostRepository extends JpaRepository<PromoPost, Long> {
    List<PromoPost> findAllByIdSeller(Long idSeller);
}
