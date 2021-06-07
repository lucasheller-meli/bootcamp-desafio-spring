package com.bootcamp.challenge.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidOperationException extends RuntimeException{
    public InvalidOperationException(String message) {
        super(message);
    }
}
