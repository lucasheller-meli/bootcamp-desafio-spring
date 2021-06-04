package com.desafio.exceptions;

public class NotFollow extends RuntimeException{
    public NotFollow(Long idUser, Long idSeller){
        super(String.format("The IDuser %s do not follow the IDseller %s.", idUser, idSeller));

    }
}
