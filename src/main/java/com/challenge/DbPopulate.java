package com.challenge;
import com.challenge.entities.Product;
import com.challenge.entities.User;
import com.challenge.entities.Seller;

import com.challenge.repositories.ProductRepository;
import com.challenge.repositories.UserRepository;
import com.challenge.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@RequiredArgsConstructor


public class DbPopulate implements CommandLineRunner {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;





    public void run(String... args) {
        User rodrigo = User.builder().userName("Rodrigo").build();
        User marianne = User.builder().userName("Marianne").build();
        User thamirez = User.builder().userName("Thamirez").build();
        User lucas = User.builder().userName("Lucas").build();
        User gabriel = User.builder().userName("Gabriel").build();
        Seller carolina = Seller.builder().sellerName("Carolina").build();
        Seller pedro = Seller.builder().sellerName("Pedro").build();
        Seller jose = Seller.builder().sellerName("José").build();
        Seller maria = Seller.builder().sellerName("Maria").build();
        Product teste = Product.builder().productName("xuaxua").build();

        userRepository.saveAll(List.of(
                rodrigo, marianne, thamirez, lucas, gabriel
        ));

        sellerRepository.saveAll(List.of(
                carolina, pedro, jose, maria
        ));

        productRepository.saveAll(List.of(
                teste
        ));



    }
}