package com.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SelfFollow extends Exception {
    public SelfFollow(Long sellerId) {
        super(String.format("Seller with id %s is trying to follow itself, this action is not permitted.", sellerId));
    }
}
