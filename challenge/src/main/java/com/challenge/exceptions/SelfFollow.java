package com.challenge.exceptions;

public class SelfFollow extends Exception {
    public SelfFollow(Long sellerId) {
        super(String.format("Seller with id %s is trying to follow itself, this action is not permitted.", sellerId));
    }
}
