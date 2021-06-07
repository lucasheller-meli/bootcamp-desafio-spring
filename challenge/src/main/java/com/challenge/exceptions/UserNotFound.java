package com.challenge.exceptions;

public class UserNotFound extends Exception {
    public UserNotFound(Long userId) {
        super(String.format("User with id %s could not be found.", userId));
    }
}
