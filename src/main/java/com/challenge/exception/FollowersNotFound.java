package com.challenge.exception;

public class FollowersNotFound extends RuntimeException {
    public FollowersNotFound(Integer retailerId) {
        super("Não existem seguidores para o Vendedor " + retailerId + ".");
    }
}
