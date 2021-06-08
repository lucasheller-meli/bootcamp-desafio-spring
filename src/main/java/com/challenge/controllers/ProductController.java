package com.challenge.controllers;

import com.challenge.dtos.*;
import com.challenge.entities.Product;
import com.challenge.exceptions.SellerNotFoundException;
import com.challenge.exceptions.UserNotFoundException;
import com.challenge.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }


    //    US 0005
    @PostMapping("/newpost")
    public ResponseEntity<Void> newProduct(@RequestBody NewProductDTO product) throws SellerNotFoundException {
        productService.newProduct(product);
        return ResponseEntity.ok().build();
    }

    //    US 0006
    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<ListPostDTO> listPostSeller(@PathVariable Long userId, @RequestParam(required = false) String order) throws UserNotFoundException {
        return ResponseEntity.ok(productService.listPostSeller(userId, order));
    }

    //    US 0010
    @PostMapping("/newpromopost")
    public ResponseEntity<Void> newPromoPost(@RequestBody NewPromoProductDTO product) throws SellerNotFoundException {
        productService.newPromoPost(product);
        return ResponseEntity.ok().build();
    }

    //    US 0011
    @GetMapping("/{sellerId}/countPromo")
    public ResponseEntity<CountPromoPostDTO> countPromoPost(@PathVariable Long sellerId) throws SellerNotFoundException {
        return ResponseEntity.ok(productService.countPromoPost(sellerId));
    }

    //    US 0012
    @GetMapping("/{sellerId}/list")
    public ResponseEntity<ListPostDTO> listPostPromoSeller(@PathVariable Long sellerId) throws SellerNotFoundException {
        return ResponseEntity.ok(productService.listPostPromoSeller(sellerId));
  }


}