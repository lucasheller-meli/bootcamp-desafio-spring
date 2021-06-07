package com.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyFollowing extends Exception {
    public AlreadyFollowing(Long followerId, Long followedId) {
        super(String.format("User with id %s is already following seller with id %s.", followerId, followedId));
    }
}
