package com.challenge.service;

import com.challenge.entity.Retailer;
import com.challenge.exception.RetailerNotFound;
import com.challenge.model.user.UserDTO;
import com.challenge.repository.UserRetailerRepository;
import com.challenge.repository.RetailerRepository;
import org.springframework.stereotype.Service;


@Service
public class RetailerService {

    private final RetailerRepository retailerRepository;

    private final UserRetailerRepository crRepository;


    public RetailerService(RetailerRepository retailerRepository, UserRetailerRepository crRepository) {
        this.retailerRepository = retailerRepository;
        this.crRepository = crRepository;
    }

    public Integer create(String name) {
        Retailer retailer = new Retailer();
        retailer.setName(name);
        retailerRepository.save(retailer);
        return retailer.getId();
    }

    public UserDTO find(Integer retailer) throws RetailerNotFound {
        return toDTO(retailerRepository.findById(retailer).orElseThrow(() -> new RetailerNotFound(retailer)));
    }

    private UserDTO toDTO(Retailer retailer) {
        return UserDTO.builder().userId(retailer.getId().toString()).userName(retailer.getName()).build();
    }


}
