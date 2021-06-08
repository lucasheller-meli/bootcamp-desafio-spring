package com.challenge.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ClientNotFound.class)
    public String clientNotFound(ClientNotFound clientNotFound) {
        return clientNotFound.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = FollowedException.class)
    public String FollowedException(FollowedException followedException) {
        return followedException.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = FollowersNotFound.class)
    public String FollowersNotFoundException(FollowersNotFound followersNotFoundException) {
        return followersNotFoundException.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = PromotionalNotFound.class)
    public String PromotionalNotFoundException(PromotionalNotFound promotionalNotFound) {
        return promotionalNotFound.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RetailerNotFound.class)
    public String RetailersNotFound(RetailerNotFound retailerNotFound) {
        return retailerNotFound.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = UnfollowException.class)
    public String UnfollowException(UnfollowException unfollowException) {
        return unfollowException.getMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = UserNotFound.class)
    public String UserNotFound(UserNotFound userNotFound) {
        return userNotFound.getMessage();
    }


}
