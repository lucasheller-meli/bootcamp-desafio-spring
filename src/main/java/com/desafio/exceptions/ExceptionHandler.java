package com.desafio.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = IdNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String idNotFoundException(IdNotFound idNotFound){
        return idNotFound.getMessage();
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotSeller.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notSeller(NotSeller notSeller){
        return notSeller.getMessage();
    }

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFollow.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFollow(NotFollow notFollow){ return notFollow.getMessage(); }
}
