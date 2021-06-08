package com.challenge.exception;

public class UserNotFound extends RuntimeException{
    public UserNotFound(Integer userId) {
        super("Usuário " + userId + " não encontrado.");
    }
}
