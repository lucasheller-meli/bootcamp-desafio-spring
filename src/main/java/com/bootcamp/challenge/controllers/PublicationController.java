package com.bootcamp.challenge.controllers;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.services.PublicationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPublication(@Valid @RequestBody PublicationRequest publicationRequest) {
        final Integer publicationId = publicationService.createPublication(publicationRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(publicationId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<Void> listPublication(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize,
                                                @RequestParam(value = "orderBy",required = false,defaultValue = "createDate") String orderBy,
                                                @RequestParam(value = "direction",required = false, defaultValue = "DESC") Sort.Direction direction) {
        publicationService.findAll(page,pageSize,orderBy,direction);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/followed/{userId}")
    public ResponseEntity<Void> listPublicationOfMyFollowedSellers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                                   @RequestParam(value = "orderBy", required = false, defaultValue = "createDate") String orderBy,
                                                                   @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction,
                                                                   @PathVariable(value = "userId") String userId) {
        publicationService.findAll(page,pageSize,orderBy,direction);
        return ResponseEntity.ok().build();
    }





}

