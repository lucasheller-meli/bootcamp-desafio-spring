package com.desafio.controller;

import com.desafio.dtos.ListPostDTO;
import com.desafio.dtos.ListPromoPostDTO;
import com.desafio.dtos.PromoPostDTO;
import com.desafio.dtos.QuantityDTO;
import com.desafio.entities.PromoPost;
import com.desafio.exceptions.IdNotFound;
import com.desafio.exceptions.NotSeller;
import com.desafio.service.PromoPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class PromoPostController {
    private final PromoPostService promoPostService;

    public PromoPostController(PromoPostService promoPostService) {
        this.promoPostService = promoPostService;
    }

    @PostMapping("/newpromopost")
    public ResponseEntity<PromoPost> postPromo(@RequestBody PromoPostDTO promoPostDTO) throws NotSeller {
       return ResponseEntity.ok(promoPostService.promoPost(promoPostDTO));
    }

    @GetMapping("/{idSeller}/countPromo")
    public ResponseEntity<QuantityDTO> promoProductQuantity(@PathVariable Long idSeller) throws IdNotFound{
        return ResponseEntity.ok(promoPostService.promoPostresQuantitu(idSeller));
    }

    @GetMapping("/{idSeller}/list")
    public ResponseEntity<ListPromoPostDTO> promoProductList(@PathVariable Long idSeller) throws IdNotFound {
        return ResponseEntity.ok(promoPostService.promoProductList(idSeller));
    }


}
