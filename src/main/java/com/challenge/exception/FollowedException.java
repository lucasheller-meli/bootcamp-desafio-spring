package com.challenge.exception;


public class FollowedException extends RuntimeException{
    public FollowedException(Integer userId) {
        super("O usuário " + userId + " não segue ninguem.");
    }
}
