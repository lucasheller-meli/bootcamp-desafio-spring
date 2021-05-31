package com.challenge;

import com.challenge.entities.Follow;
import com.challenge.entities.User;
import com.challenge.repositories.FollowRepository;
import com.challenge.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class PopulateDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

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
    }
}
