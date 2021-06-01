package com.desafio.repository;

import com.desafio.entities.Posters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posters, Long> {
}
