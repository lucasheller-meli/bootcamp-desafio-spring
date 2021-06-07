package com.challenge.services;

import com.challenge.dtos.CreateProductRequest;
import com.challenge.entities.Product;
import com.challenge.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product create(CreateProductRequest createProductRequest) {
        return productRepository.save(Product.builder()
                .name(createProductRequest.getName())
                .type(createProductRequest.getType())
                .brand(createProductRequest.getBrand())
                .color(createProductRequest.getColor())
                .note(createProductRequest.getNote())
                .build());
    }
}
