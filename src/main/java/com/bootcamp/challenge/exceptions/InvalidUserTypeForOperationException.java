package com.bootcamp.challenge.exceptions;

public class InvalidUserTypeForOperationException extends RuntimeException {

    public InvalidUserTypeForOperationException(String message) {
        super(message);
    }
}
