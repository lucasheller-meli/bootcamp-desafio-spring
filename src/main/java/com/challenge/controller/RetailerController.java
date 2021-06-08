package com.challenge.controller;

import com.challenge.exception.RetailerNotFound;
import com.challenge.model.user.UserDTO;
import com.challenge.service.RetailerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retailers")
public class RetailerController {

    private final RetailerService service;

    public RetailerController(RetailerService service) {
        this.service = service;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody String name) {
        return service.create(name);
    }


    @GetMapping("/{retailer}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO find(@PathVariable Integer retailer) throws RetailerNotFound {
        return service.find(retailer);
    }

}
