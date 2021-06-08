package com.challenge.controller;

import com.challenge.model.publication.CreatePublicationDTO;
import com.challenge.model.publication.PublicationDTO;
import com.challenge.model.user.RetailerPromoProductsDTO;
import com.challenge.service.PublicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PublicationController {

    private final PublicationService service;

    public PublicationController(PublicationService service) {
        this.service = service;
    }

    @PostMapping("/newpost")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreatePublicationDTO dto) {
        service.publish(dto);
    }

    @PostMapping("/newpromopost")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPromo(@RequestBody CreatePublicationDTO dto) {
        service.publish(dto);
    }

    @GetMapping("/{userId}/countPromo")
    @ResponseStatus(HttpStatus.OK)
    public RetailerPromoProductsDTO countPromo(@PathVariable Integer userId) {
        return service.countPromo(userId);
    }

    @GetMapping("/followed/{userId}/list")
    @ResponseStatus(HttpStatus.OK)
    public List<PublicationDTO> find(@PathVariable Integer userId, @RequestParam(required = false) String order) {
       return service.find(userId,order);
    }
}
