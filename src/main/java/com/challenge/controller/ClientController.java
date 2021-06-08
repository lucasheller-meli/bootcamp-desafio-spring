package com.challenge.controller;

import com.challenge.entity.User;
import com.challenge.exception.ClientNotFound;
import com.challenge.model.user.UserDTO;
import com.challenge.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }


    @PostMapping()
    public ResponseEntity<Integer> create(@RequestBody String name) {
        return ResponseEntity.ok(service.create(name));
    }

    @GetMapping("/{client}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO find(@PathVariable Integer client) throws ClientNotFound {
        return service.find(client);
    }

}
