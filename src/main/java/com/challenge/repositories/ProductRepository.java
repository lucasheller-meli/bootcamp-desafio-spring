package com.challenge.repositories;


import com.challenge.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;


public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllBySellerIdInOrderByDateAsc(Set<Long> idSellers);
    List<Product> findAllBySellerIdInOrderByDateDesc(Set<Long> idSellers);

}
