package com.challenge;

import com.challenge.entities.*;
import com.challenge.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulateDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final FollowRepository followRepository;
    private final ProductRepository productRepository;
    private final PostRepository postRepository;
    private final PromotionalPostRepository promotionalPostRepository;

    public void run(String... args) {
        Seller rodrigo = Seller.builder().name("Rodrigo").build();
        Seller marianne = Seller.builder().name("Marianne").build();
        Seller thamirez = Seller.builder().name("Thamirez").build();
        Seller lucas = Seller.builder().name("Lucas").build();
        Seller carolina = Seller.builder().name("Carolina").build();

        sellerRepository.saveAll(List.of(rodrigo, marianne, thamirez, lucas, carolina));

        User pedro = User.builder().name("Pedro").build();
        User jose = User.builder().name("José").build();
        User maria = User.builder().name("Maria").build();

        userRepository.saveAll(List.of(pedro, jose, maria));

        followRepository.saveAll(List.of(
                Follow.builder().follower(rodrigo).followed(marianne).build(),
                Follow.builder().follower(rodrigo).followed(thamirez).build(),
                Follow.builder().follower(rodrigo).followed(lucas).build(),
                Follow.builder().follower(marianne).followed(rodrigo).build(),
                Follow.builder().follower(marianne).followed(thamirez).build(),
                Follow.builder().follower(marianne).followed(lucas).build(),
                Follow.builder().follower(thamirez).followed(carolina).build(),
                Follow.builder().follower(lucas).followed(marianne).build(),
                Follow.builder().follower(lucas).followed(thamirez).build(),
                Follow.builder().follower(lucas).followed(rodrigo).build(),
                Follow.builder().follower(carolina).followed(marianne).build(),
                Follow.builder().follower(carolina).followed(rodrigo).build(),
                Follow.builder().follower(pedro).followed(marianne).build(),
                Follow.builder().follower(jose).followed(marianne).build()
        ));

        Product cadeira = Product.builder().name("cadeira").type("gamer").color("preto").brand("racer").note("confortável").build();
        Product monitor = Product.builder().name("monitor").type("gamer").color("preto").brand("lg").note("29 polegadas").build();
        Product notebook = Product.builder().name("macbook").type("produtividade").color("cinza").brand("apple").note("chique").build();
        Product mesa = Product.builder().name("mesa").type("escritório").color("marrom").brand("genius").note("altura regulável").build();
        Product fone = Product.builder().name("fone").type("bluetooth").color("vermelho").brand("jbl").note("alta definição").build();

        productRepository.saveAll(List.of(
                cadeira, monitor, notebook, mesa, fone
        ));

        postRepository.saveAll(List.of(
                Post.builder().seller(rodrigo).product(cadeira).category(1).price(1000.0).date(LocalDate.now()).build(),
                Post.builder().seller(rodrigo).product(cadeira).category(1).price(1200.0).date(LocalDate.now().minusDays(1)).build(),
                Post.builder().seller(rodrigo).product(cadeira).category(1).price(1800.0).date(LocalDate.now().minusWeeks(2)).build(),
                Post.builder().seller(marianne).product(cadeira).category(1).price(800.0).date(LocalDate.now()).build(),
                Post.builder().seller(marianne).product(cadeira).category(1).price(900.0).date(LocalDate.now().minusDays(3)).build(),
                Post.builder().seller(marianne).product(cadeira).category(1).price(1900.0).date(LocalDate.now().minusWeeks(2)).build(),
                Post.builder().seller(lucas).product(cadeira).category(1).price(1200.0).date(LocalDate.now()).build(),
                Post.builder().seller(lucas).product(cadeira).category(1).price(1400.0).date(LocalDate.now().minusDays(5)).build(),
                Post.builder().seller(lucas).product(cadeira).category(1).price(1700.0).date(LocalDate.now().minusWeeks(2)).build(),
                Post.builder().seller(rodrigo).product(fone).category(3).price(500.0).date(LocalDate.now()).build(),
                Post.builder().seller(carolina).product(monitor).category(6).price(2152.20).date(LocalDate.now().minusDays(1)).build(),
                Post.builder().seller(thamirez).product(mesa).category(23).price(3000.0).date(LocalDate.now()).build(),
                Post.builder().seller(thamirez).product(notebook).category(99).price(30329.0).date(LocalDate.now().minusDays(7)).build(),
                Post.builder().seller(carolina).product(notebook).category(99).price(28329.0).date(LocalDate.now()).build()
        ));

        promotionalPostRepository.saveAll(List.of(
                PromotionalPost.builder().seller(rodrigo).product(cadeira).category(1).price(1200.0).date(LocalDate.now().minusDays(2)).hasPromotion(true).discount(0.22).build(),
                PromotionalPost.builder().seller(rodrigo).product(cadeira).category(1).price(1800.0).date(LocalDate.now().minusWeeks(3)).hasPromotion(true).discount(0.21).build(),
                PromotionalPost.builder().seller(marianne).product(cadeira).category(1).price(900.0).date(LocalDate.now().minusDays(4)).hasPromotion(true).discount(0.37).build(),
                PromotionalPost.builder().seller(marianne).product(cadeira).category(1).price(1900.0).date(LocalDate.now().minusWeeks(4)).hasPromotion(true).discount(0.51).build(),
                PromotionalPost.builder().seller(lucas).product(cadeira).category(1).price(1400.0).date(LocalDate.now().minusDays(5)).hasPromotion(true).discount(0.25).build(),
                PromotionalPost.builder().seller(lucas).product(cadeira).category(1).price(1700.0).date(LocalDate.now().minusWeeks(4)).hasPromotion(true).discount(0.03).build(),
                PromotionalPost.builder().seller(carolina).product(monitor).category(6).price(2152.20).date(LocalDate.now().minusDays(2)).hasPromotion(true).discount(0.33).build(),
                PromotionalPost.builder().seller(thamirez).product(notebook).category(99).price(30329.0).date(LocalDate.now().minusDays(8)).hasPromotion(true).discount(0.18).build()
        ));
    }
}
