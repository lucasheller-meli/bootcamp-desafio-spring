package com.desafio.service;

import com.desafio.dtos.FollowDTO;
import com.desafio.dtos.PostDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.entities.Posters;
import com.desafio.entities.Seller;
import com.desafio.repository.PostRepository;
import com.desafio.repository.SellerRepository;
import com.desafio.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PostRepository postRepository;

    public SellerService(SellerRepository sellerRepository, PostRepository postRepository){
        this.sellerRepository = sellerRepository;
        this.postRepository = postRepository;
    }

    public QuantityDTO quantityFollowers(Long idSeller){
        Seller seller = sellerRepository.getById(idSeller);
        QuantityDTO quantityDTO = QuantityDTO.builder()
                .name(seller.getNameSaler())
                .id(seller.getIdSaler())
                .quantity(seller.getFollowers().size())
                .build();
        return quantityDTO;
    }

    public FollowDTO listFollowers(Long idSeller){
        FollowDTO followDTO = FollowDTO.builder().name(sellerRepository.getById(idSeller).getNameSaler())
                .id(sellerRepository.getById(idSeller).getIdSaler())
                .list(sellerRepository.getById(idSeller).getFollowers())
                .build();

        return followDTO;
    }

    //como pegar o idpost
    public Posters post(PostDTO postDTO){
        Posters post = Posters.builder()
                .idSaler(postDTO.getIdSaler())
                .date(postDTO.getDate())
                .detail(postDTO.getDetail())
                .category(postDTO.getCategory())
                .price(postDTO.getPrice())
                .build();

        //postRepository.save(post);
        return post;
    }


}
