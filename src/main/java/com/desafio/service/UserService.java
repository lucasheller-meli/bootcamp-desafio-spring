package com.desafio.service;

import com.desafio.dtos.FollowDTO;
import com.desafio.entities.Seller;
import com.desafio.entities.User;
import com.desafio.exceptions.IdNotFound;
import com.desafio.exceptions.NotFollow;
import com.desafio.repository.SellerRepository;
import com.desafio.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserService(UserRepository userRepository, SellerRepository sellerRepository){
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    public void follow(Long idUser, Long idSeller) throws IdNotFound{

        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));
        User user = userRepository.findById(idUser).orElseThrow(() -> new IdNotFound(idUser));

        Map<Long, String> mapFollowing = new HashMap<>(user.getFollowing());
        mapFollowing.put(idSeller, seller.getNameSaler());

        Map<Long, String> mapFollowers = new HashMap<>(seller.getFollowers());
        mapFollowers.put(idUser, user.getUserName());

        user.setFollowing(mapFollowing);
        seller.setFollowers(mapFollowers);

        userRepository.save(user);
        sellerRepository.save(seller);
    }

    public FollowDTO listFollowing(Long idUser, String order) throws IdNotFound{
        userRepository.findById(idUser).orElseThrow(() -> new IdNotFound(idUser));

        Map<Long, String> mapFollowing = new HashMap<>(userRepository.getById(idUser).getFollowing());

        //ordena o map
        if(order!= null){
            mapFollowing = sortByValue(mapFollowing, order);
        }


        return FollowDTO.builder().name(userRepository.getById(idUser).getUserName())
                .id(userRepository.getById(idUser).getIdUser())
                .list(mapFollowing)
                .build();
    }

    public void unfollow(Long idUser, Long idSeller) throws IdNotFound, NotFollow{
        Seller seller = sellerRepository.findById(idSeller).orElseThrow(() -> new IdNotFound(idSeller));
        User user = userRepository.findById(idUser).orElseThrow(() -> new IdNotFound(idUser));

        Map<Long, String> mapFollowing = new HashMap<>(user.getFollowing());
        Map<Long, String> mapFollowers = new HashMap<>(seller.getFollowers());

        boolean aux = false;

        for (Map.Entry<Long, String> entry: mapFollowing.entrySet() ) {
            if (entry.getValue().equals(seller.getNameSaler())) {
                aux = true;
                break;
            }
        }

        if(aux){
            mapFollowing.remove(idSeller, seller.getNameSaler());
            mapFollowers.remove(idUser, user.getUserName());

            user.setFollowing(mapFollowing);
            seller.setFollowers(mapFollowers);

            userRepository.save(user);
            sellerRepository.save(seller);
        }else {
            throw new NotFollow(idUser, idSeller);
        }

    }

    public Map<Long, String> sortByValue(Map<Long, String> map, String order){
        List<Map.Entry<Long, String>> list = new ArrayList<>(map.entrySet());
        Map<Long, String> result = new TreeMap<>();
        //Ascedente 0 Descedente 1
        if (order.equals("name_asc")){
            list.sort(Map.Entry.comparingByValue());
            result = new TreeMap<>();
            for(Map.Entry<Long, String> entry : list){
                result.put(entry.getKey(), entry.getValue());
            }
        }
        else if(order.equals("name_desc")) {
            result = new TreeMap<>(Collections.reverseOrder());
            result.putAll(map);
        }

       return result;
    }

}
