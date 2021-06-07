package com.challenge.repositories;

import com.challenge.entities.PromotionalPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionalPostRepository extends JpaRepository<PromotionalPost, Long> {
    List<PromotionalPost> findAllBySellerId(Long sellerId);
}
