package com.challenge.services;

import com.challenge.dtos.CreateSellerRequest;
import com.challenge.entities.Seller;
import com.challenge.exceptions.SellerNotFound;
import com.challenge.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final SellerRepository sellerRepository;


    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Seller findById(Long id) throws SellerNotFound {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerNotFound(id));
    }

    public Seller create(CreateSellerRequest createSellerRequest) {
        return sellerRepository.save(Seller.builder().name(createSellerRequest.getName()).build());
    }
}
