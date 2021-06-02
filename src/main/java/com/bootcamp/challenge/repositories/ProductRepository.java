package com.bootcamp.challenge.repositories;

import com.bootcamp.challenge.entities.FollowEntity;
import com.bootcamp.challenge.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
