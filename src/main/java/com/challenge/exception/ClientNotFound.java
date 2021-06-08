package com.challenge.exception;

public class ClientNotFound extends RuntimeException {
    public ClientNotFound(Integer client) {
        super("Cliente com o ID " + client + " não foi encotrado.");
    }
}
