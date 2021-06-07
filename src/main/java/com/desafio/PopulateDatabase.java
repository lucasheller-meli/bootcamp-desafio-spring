package com.desafio;

import com.desafio.entities.Posters;
import com.desafio.entities.Seller;
import com.desafio.entities.User;
import com.desafio.repository.PostRepository;
import com.desafio.repository.SellerRepository;
import com.desafio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulateDatabase implements CommandLineRunner {

        private final UserRepository userRepository;
        private final SellerRepository sellerRepository;
        private final PostRepository postRepository;


    public void run(String... args) {
            User rodrigo = User.builder().userName("Rodrigo").build();
            User marianne = User.builder().userName("Marianne").build();
            User thamirez = User.builder().userName("Thamirez").build();
            User lucas = User.builder().userName("Lucas").build();
            Seller carolina = Seller.builder().nameSaler("Carolina").build();
            Seller pedro = Seller.builder().nameSaler("Pedro").build();
            Seller jose = Seller.builder().nameSaler("José").build();
            Seller maria = Seller.builder().nameSaler("Maria").build();

            userRepository.saveAll(List.of(
                    rodrigo, marianne, thamirez, lucas
            ));
            sellerRepository.saveAll(List.of(
                    carolina, pedro, jose, maria
            ));


        }
}
