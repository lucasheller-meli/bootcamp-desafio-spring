package com.challenge.services;

import com.challenge.dtos.CreateUserDTO;
import com.challenge.entities.Seller;
import com.challenge.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    public Seller save(CreateUserDTO createUserDTO) {
        return sellerRepository.save(Seller.builder().name(createUserDTO.getName()).build());
    }
}