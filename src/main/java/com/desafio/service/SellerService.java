package com.desafio.service;

import com.desafio.dtos.FollowDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.entities.Seller;
import com.desafio.exceptions.IdNotFound;
import com.desafio.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;

    public SellerService(SellerRepository sellerRepository, UserService userService){
        this.sellerRepository = sellerRepository;
        this.userService = userService;
    }

    public QuantityDTO quantityFollowers(Long idSeller) throws IdNotFound {
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));
        return QuantityDTO.builder()
                .name(seller.getNameSaler())
                .id(seller.getIdSaler())
                .quantity(seller.getFollowers().size())
                .build();
    }

    public FollowDTO listFollowers(Long idSeller, String order) throws IdNotFound{
        sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));

        Map<Long, String> mapFollowers = sellerRepository.getById(idSeller).getFollowers();

        if(order!= null){
            mapFollowers= userService.sortByValue(mapFollowers, order);
        }

        return FollowDTO.builder().name(sellerRepository.getById(idSeller).getNameSaler())
                .id(sellerRepository.getById(idSeller).getIdSaler())
                .list(mapFollowers)
                .build();
    }

    public Optional<Seller> findByIdSeller(Long idSeller){ return sellerRepository.findById(idSeller); }


}
