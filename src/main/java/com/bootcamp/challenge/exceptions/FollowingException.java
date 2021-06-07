package com.bootcamp.challenge.exceptions;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EntityNotFoundException;

@Getter
@Setter
public class FollowingException extends EntityNotFoundException {
    public FollowingException(String message) {
        super(message);
    }
}
