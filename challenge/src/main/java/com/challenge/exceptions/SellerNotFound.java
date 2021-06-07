package com.challenge.exceptions;

public class SellerNotFound extends Exception {
    public SellerNotFound(Long sellerId) {
        super(String.format("Seller with id %s could not be found.", sellerId));
    }
}
