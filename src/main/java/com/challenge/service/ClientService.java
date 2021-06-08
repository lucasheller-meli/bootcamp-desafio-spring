package com.challenge.service;

import com.challenge.entity.Client;
import com.challenge.entity.User;
import com.challenge.exception.ClientNotFound;
import com.challenge.model.user.UserDTO;
import com.challenge.repository.ClientRepository;
import org.springframework.stereotype.Service;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Integer create(String name) {
        Client client = new Client();
        client.setName(name);
        clientRepository.save(client);
        return client.getId();
    }

    public UserDTO find(Integer client) throws ClientNotFound {
       return toDTO(clientRepository.findById(client).orElseThrow(() -> new ClientNotFound(client)));
    }

    private UserDTO toDTO(Client client) {
        return UserDTO.builder().userName(client.getName()).userId(client.getId().toString()).build();
    }

}
