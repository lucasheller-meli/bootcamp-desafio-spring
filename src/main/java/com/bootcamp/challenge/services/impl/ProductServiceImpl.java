package com.bootcamp.challenge.services.impl;

import com.bootcamp.challenge.controllers.request.ProductRequest;
import com.bootcamp.challenge.entities.ProductEntity;
import com.bootcamp.challenge.entities.UserEntity;
import com.bootcamp.challenge.exceptions.NotFoundException;
import com.bootcamp.challenge.repositories.ProductRepository;
import com.bootcamp.challenge.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        final ProductEntity productEntity = convertRequestToProductEntity(productRequest);
        return Optional.of(productEntity)
                .map(productRepository::save)
                .map(ProductEntity::getId)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public ProductEntity findById(Integer id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("O Produto de ID nao foi encontrado" + id));
    }

    private ProductEntity convertRequestToProductEntity(ProductRequest productRequest) {
        return ProductEntity
                .builder()
                .name(productRequest.getName())
                .type(productRequest.getType())
                .brand(productRequest.getBrand())
                .color(productRequest.getColor())
                .notes(productRequest.getNotes())
                .build();

    }
}
