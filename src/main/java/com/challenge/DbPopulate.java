package com.challenge;
import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.entities.Seller;
import com.challenge.repositories.FollowRepository;
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
    private final FollowRepository followRepository;
    public void run(String... args) {
        User rodrigo = User.builder().name("Rodrigo").build();
        User marianne = User.builder().name("Marianne").build();
        User thamirez = User.builder().name("Thamirez").build();
        User lucas = User.builder().name("Lucas").build();
        User gabriel = User.builder().name("Gabriel").build();
        Seller carolina = Seller.builder().name("Carolina").build();
        Seller pedro = Seller.builder().name("Pedro").build();
        Seller jose = Seller.builder().name("José").build();
        Seller maria = Seller.builder().name("Maria").build();
        userRepository.saveAll(List.of(
                rodrigo, marianne, thamirez, lucas, gabriel
        ));
        sellerRepository.saveAll(List.of(
                carolina, pedro, jose, maria
        ));
        followRepository.saveAll(List.of(
                Follow.builder().follower(thamirez).followed(carolina).build(),
                Follow.builder().follower(thamirez).followed(pedro).build(),
                Follow.builder().follower(thamirez).followed(jose).build(),
                Follow.builder().follower(marianne).followed(carolina).build(),
                Follow.builder().follower(marianne).followed(maria).build(),
                Follow.builder().follower(rodrigo).followed(maria).build(),
                Follow.builder().follower(rodrigo).followed(pedro).build(),
                Follow.builder().follower(gabriel).followed(maria).build(),
                Follow.builder().follower(gabriel).followed(pedro).build(),
                Follow.builder().follower(lucas).followed(jose).build(),
                Follow.builder().follower(lucas).followed(maria).build()
        ));
    }
}