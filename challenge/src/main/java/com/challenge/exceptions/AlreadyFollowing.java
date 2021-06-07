package com.challenge.exceptions;

public class AlreadyFollowing extends Exception {
    public AlreadyFollowing(Long followerId, Long followedId) {
        super(String.format("User with id %s is already following seller with id %s.", followerId, followedId));
    }
}
