package com.challenge;

import com.challenge.entities.Follow;
import com.challenge.entities.Post;
import com.challenge.entities.Product;
import com.challenge.entities.User;
import com.challenge.repositories.FollowRepository;
import com.challenge.repositories.PostRepository;
import com.challenge.repositories.ProductRepository;
import com.challenge.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PopulateDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ProductRepository productRepository;
    private final PostRepository postRepository;

    public void run(String... args) {
        User rodrigo = User.builder().name("Rodrigo").build();
        User marianne = User.builder().name("Marianne").build();
        User thamirez = User.builder().name("Thamirez").build();
        User lucas = User.builder().name("Lucas").build();
        User carolina = User.builder().name("Carolina").build();
        User pedro = User.builder().name("Pedro").build();
        User jose = User.builder().name("José").build();
        User maria = User.builder().name("Maria").build();

        userRepository.saveAll(List.of(
                rodrigo, marianne, thamirez, lucas, carolina, pedro, jose, maria
        ));

        followRepository.saveAll(List.of(
                Follow.builder().follower(rodrigo).followed(marianne).build(),
                Follow.builder().follower(rodrigo).followed(thamirez).build(),
                Follow.builder().follower(rodrigo).followed(lucas).build(),
                Follow.builder().follower(marianne).followed(rodrigo).build(),
                Follow.builder().follower(marianne).followed(thamirez).build(),
                Follow.builder().follower(marianne).followed(lucas).build(),
                Follow.builder().follower(thamirez).followed(pedro).build(),
                Follow.builder().follower(thamirez).followed(maria).build(),
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
                Post.builder().user(rodrigo).product(cadeira).category(1).price(1000.0).build(),
                Post.builder().user(marianne).product(cadeira).category(1).price(800.0).build(),
                Post.builder().user(lucas).product(cadeira).category(1).price(1200.0).build(),
                Post.builder().user(rodrigo).product(fone).category(3).price(500.0).build(),
                Post.builder().user(carolina).product(monitor).category(6).price(2152.20).build(),
                Post.builder().user(pedro).product(mesa).category(23).price(3000.0).build(),
                Post.builder().user(thamirez).product(notebook).category(99).price(30329.0).build(),
                Post.builder().user(jose).product(notebook).category(99).price(28329.0).build()
        ));
    }
}
