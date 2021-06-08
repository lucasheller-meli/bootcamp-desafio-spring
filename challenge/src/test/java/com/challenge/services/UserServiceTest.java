package com.challenge.services;

import com.challenge.dtos.CreateUserRequest;
import com.challenge.entities.User;
import com.challenge.exceptions.UserNotFound;
import com.challenge.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void whenFindAllIsCalledThenAListOfUsersIsReturned() {
        User user = User.builder().id(1L).name("Rodrigo").build();

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> users = userService.findAll();
        assertThat(users, contains(user));
    }

    @Test
    void whenFindAllIsCalledThenAEmptyListIsReturned() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> users = userService.findAll();
        assertThat(users, empty());
    }

    @Test
    void whenFindByIdIsCalledWithUserIdThenAUserIsReturned() throws UserNotFound {
        Long requestedId = 1L;
        User expectedUser = User.builder().id(requestedId).name("Rodrigo").build();

        when(userRepository.findById(requestedId)).thenReturn(Optional.of(expectedUser));

        User foundUser = userService.findById(requestedId);
        assertThat(foundUser, equalTo(expectedUser));
    }

    @Test
    void whenFindByIdIsCalledWithNotRegisteredUserIdThenAExceptionIsThrown() {
        Long requestedId = 1L;

        when(userRepository.findById(requestedId)).thenReturn(Optional.empty());

        assertThrows(UserNotFound.class, () -> userService.findById(requestedId));
    }

    @Test
    void whenCreateIsCalledWithACreateUserRequestThenAUserIsCreated() {
        CreateUserRequest request = CreateUserRequest.builder().name("Rodrigo").build();
        User expectedUser = User.builder().name(request.getName()).build();

        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        User createdUser = userService.create(request);
        assertThat(createdUser, equalTo(expectedUser));
    }
}