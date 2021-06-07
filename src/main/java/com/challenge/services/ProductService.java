package com.challenge.services;

import com.challenge.dtos.ListPostDTO;
import com.challenge.dtos.NewProductDTO;
import com.challenge.entities.Product;

import com.challenge.entities.Seller;
import com.challenge.entities.User;
import com.challenge.exceptions.SellerNotFoundException;
import com.challenge.exceptions.UserNotFoundException;
import com.challenge.repositories.SellerRepository;
import com.challenge.repositories.ProductRepository;
import com.challenge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private static final Integer WEEKS =  2;


    public ProductService(ProductRepository productRepository, SellerRepository sellerRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
        this.userRepository = userRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void newProduct(NewProductDTO newProductDTO) throws SellerNotFoundException{
        Seller seller = sellerRepository.findById(newProductDTO.getSellerId()).orElseThrow(SellerNotFoundException::new);

        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(newProductDTO.getDate(), dtf);

        Product post = Product.builder()
                .idPost(newProductDTO.getIdPost())
                .sellerId(seller.getSellerId())
                .date(date)
                .productName(newProductDTO.getProductName())
                .type(newProductDTO.getType())
                .brand(newProductDTO.getBrand())
                .color(newProductDTO.getColor())
                .notes(newProductDTO.getNotes())
                .category(newProductDTO.getCategory())
                .price(newProductDTO.getPrice())
                .build();


        productRepository.save(post);
    }

    public ListPostDTO listPostSeller(Long userId, String order) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Map<Long, String> mapFollowing = user.getFollower();

        List<Product> listPost;

        Set<Long> setSeller = mapFollowing.keySet();

         if ("date_desc".equals(order)){
            listPost = productRepository.findAllBySellerIdInOrderByDateDesc(setSeller);
        }else{
             listPost = productRepository.findAllBySellerIdInOrderByDateAsc(setSeller);
         }

        return ListPostDTO.builder()
            .userId(user.getUserId())
            .userName(user.getUserName())
            .list(listPost.stream().filter(post -> post.getDate().isAfter(LocalDate.now().minusWeeks(WEEKS))).collect(Collectors.toList()))
            .build();
    }
}
