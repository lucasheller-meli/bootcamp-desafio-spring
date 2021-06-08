package com.challenge.exception;


public class UnfollowException extends RuntimeException{

    public UnfollowException(Integer userId, Integer retailerId) {
        super("Relação de seguidor entre o usuário " + userId +" e o vendedor" + retailerId + " não foi encontrada.");
    }
}
