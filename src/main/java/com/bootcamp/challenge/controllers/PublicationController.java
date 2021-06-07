package com.bootcamp.challenge.controllers;

import com.bootcamp.challenge.controllers.request.PublicationRequest;
import com.bootcamp.challenge.controllers.response.FollowedPublicationResponse;
import com.bootcamp.challenge.controllers.response.PublicationCountResponse;
import com.bootcamp.challenge.controllers.response.PublicationResponse;
import com.bootcamp.challenge.services.PublicationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;

@RestController
@Api(tags = {"Publicacao"})
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cria uma publicacao.")
    public ResponseEntity<Void> createPublication(@Valid @RequestBody PublicationRequest publicationRequest) {
        final Integer publicationId = publicationService.createPublication(publicationRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(publicationId).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/followed/{userId}")
    @ApiOperation(value = "Lista todas as publicacoes de todos os vendedores que o usuario segue.")
    public ResponseEntity<FollowedPublicationResponse> listPublicationOfMyFollowedSellers(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                                          @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
                                                                                          @RequestParam(value = "orderBy", required = false, defaultValue = "createDate") String orderBy,
                                                                                          @RequestParam(value = "hasPromo", required = false, defaultValue = "false") Boolean hasPromo,
                                                                                          @RequestParam(value = "direction", required = false, defaultValue = "DESC") Sort.Direction direction,
                                                                                          @PathVariable(value = "userId") Integer userId) {

        return ResponseEntity.ok(publicationService.findAllProductsFromFollowed(userId, page, pageSize, orderBy, hasPromo, direction));
    }

    @GetMapping("/count/{userId}")
    @ApiOperation(value = "Conta a quantidade de publicacao de um usuario.")
    public ResponseEntity<PublicationCountResponse> countPublications(@PathVariable(value = "userId") Integer userId,
                                                                      @RequestParam(value = "hasPromo", required = false, defaultValue = "false") Boolean hasPromo) {
        return ResponseEntity.ok(publicationService.countPublication(userId,hasPromo));
    }

    @GetMapping("/{userId}")
    @ApiOperation(value = "Lista as publicacoes de um usuario.")
    public ResponseEntity<List<PublicationResponse>> listPublicationByUserId(@PathVariable(value = "userId") Integer userId,
                                                                             @RequestParam(value = "hasPromo", required = false, defaultValue = "false") Boolean hasPromo,
                                                                             @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                             @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(publicationService.findAll(userId, hasPromo, page,pageSize));
    }


}

