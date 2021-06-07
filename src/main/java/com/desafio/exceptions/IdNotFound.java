package com.desafio.exceptions;


public class IdNotFound extends RuntimeException {
    public IdNotFound(Long id){
        super(String.format("The ID %s could not be found.", id));
    }
}
