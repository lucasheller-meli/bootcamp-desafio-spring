package com.desafio.exceptions;

public class NotSeller extends RuntimeException{
    public NotSeller(){
        super("ID is not IdSeller.");
    }
}
