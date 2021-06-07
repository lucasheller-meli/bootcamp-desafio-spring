package com.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SellerNotFound extends Exception {
    public SellerNotFound(Long sellerId) {
        super(String.format("Seller with id %s could not be found.", sellerId));
    }
}
