package com.challenge.controllers;

import com.challenge.dtos.CreateUserRequest;
import com.challenge.entities.User;
import com.challenge.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private static final String USER_API_URL = "/users";

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void whenGetIsCalledThenAListOfUsersIsReturned() throws Exception {
        User user = User.builder().name("Rodrigo").build();

        when(userService.findAll()).thenReturn(List.of(user));
        mockMvc.perform(get(USER_API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(user.getName())));
    }

    @Test
    void whenGetIsCalledThenAEmptyListIsReturned() throws Exception {
        when(userService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get(USER_API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", empty()));
    }

    @Test
    void whenPostIsCalledWithACreateUserRequestThenAUserIsCreated() throws Exception {
        CreateUserRequest request = CreateUserRequest.builder().name("Rodrigo").build();

        User createdUser = User.builder().name(request.getName()).build();

        when(userService.create(request)).thenReturn(createdUser);

        mockMvc.perform(post(USER_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(request.getName())));
    }

    @Test
    void whenPostIsCalledWithoutARequiredFieldThenAErrorIsReturned() throws Exception {
        CreateUserRequest emptyRequest = CreateUserRequest.builder().build();

        mockMvc.perform(post(USER_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(emptyRequest)))
                .andExpect(status().isBadRequest());
    }
}