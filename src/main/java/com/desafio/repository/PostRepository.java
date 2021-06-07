package com.desafio.repository;

import com.desafio.entities.Posters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Posters, Long> {
    List<Posters> findAllByIdSellerInOrderByDateAsc(Set<Long> idSellers);
    List<Posters> findAllByIdSellerInOrderByDateDesc(Set<Long> idSellers);

}
