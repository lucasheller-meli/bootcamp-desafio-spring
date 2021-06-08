package com.challenge.exception;

public class RetailerNotFound extends RuntimeException {

    public RetailerNotFound(Integer id) {
        super("Não foi encontrado um Vendedor com o ID "+ id + ".");
    }
}
