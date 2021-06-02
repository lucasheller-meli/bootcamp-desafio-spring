package com.bootcamp.challenge.services;

import com.bootcamp.challenge.controllers.request.ProductRequest;
import com.bootcamp.challenge.entities.ProductEntity;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    ProductEntity findById(Integer id);
}
