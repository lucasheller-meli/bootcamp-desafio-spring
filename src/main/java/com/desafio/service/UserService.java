package com.desafio.service;

import com.desafio.dtos.FollowDTO;
import com.desafio.entities.Seller;
import com.desafio.entities.User;
import com.desafio.repository.SellerRepository;
import com.desafio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository; //isso nao eh uma bo pratica

    public UserService(UserRepository userRepository, SellerRepository sellerRepository){
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    public void follow(Long idUser, Long idSeller){
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(); //isso nao eh um boa pratica
        User user = userRepository.findById(idUser).orElseThrow();

        Map<Long, String> mapFollowing = new HashMap<>(user.getFollowing());
        mapFollowing.put(idSeller, seller.getNameSaler());

        Map<Long, String> mapFollowers = new HashMap<>(seller.getFollowers());
        mapFollowers.put(idUser, user.getUserName());

        user.setFollowing(mapFollowing);
        seller.setFollowers(mapFollowers);

        userRepository.save(user);
        sellerRepository.save(seller);
    }

    public FollowDTO listFollowing(Long idUser){
        FollowDTO followDTO = FollowDTO.builder().name(userRepository.getById(idUser).getUserName())
                .id(userRepository.getById(idUser).getIdUser())
                .list(userRepository.getById(idUser).getFollowing())
                .build();
        return followDTO;
    }

    public void unfollow(Long idUser, Long idSeller){
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(); //isso nao eh um boa pratica
        User user = userRepository.findById(idUser).orElseThrow();

        Map<Long, String> mapFollowing = new HashMap<>(user.getFollowing());
        mapFollowing.remove(idSeller, seller.getNameSaler());

        Map<Long, String> mapFollowers = new HashMap<>(seller.getFollowers());
        mapFollowers.remove(idUser, user.getUserName());

        user.setFollowing(mapFollowing);
        seller.setFollowers(mapFollowers);

        userRepository.save(user);
        sellerRepository.save(seller);
    }

}
