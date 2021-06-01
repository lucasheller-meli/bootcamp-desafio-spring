package com.bootcamp.challenge.exceptions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityNotFoundException;
import java.util.function.Supplier;

@Getter
@Setter
public class NotFoundException extends EntityNotFoundException  {

    public NotFoundException(String message) {
        super(message);
    }
}
