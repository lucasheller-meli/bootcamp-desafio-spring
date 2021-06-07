package com.bootcamp.challenge.exceptions.handler;

import com.bootcamp.challenge.exceptions.FollowingException;
import com.bootcamp.challenge.exceptions.InvalidOperationException;
import com.bootcamp.challenge.exceptions.InvalidUserTypeForOperationException;
import com.bootcamp.challenge.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseException handleNotFoundException(NotFoundException notFoundException) {
        log.error("m=handleNotFoundException, e={}", notFoundException.getMessage());
        return new ResponseException(notFoundException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = FollowingException.class)
    public ResponseException handleFollowingException(FollowingException followingException) {
        log.error("m=handleFollowingException, e={}", followingException.getMessage());
        return new ResponseException(followingException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidUserTypeForOperationException.class)
    public ResponseException handleInvalidUserTypeForOperationException(InvalidUserTypeForOperationException invalidUserTypeForOperationException) {
        log.error("m=handleInvalidUserTypeForOperationException, e={}", invalidUserTypeForOperationException.getMessage());
        return new ResponseException(invalidUserTypeForOperationException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseException handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        log.error("m=httpMessageNotReadableException, e={}", httpMessageNotReadableException.getMessage());
        return new ResponseException(httpMessageNotReadableException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidOperationException.class)
    public ResponseException handleInvalidOperationException(InvalidOperationException invalidOperationException) {
        log.error("m=httpMessageNotReadableException, e={}", invalidOperationException.getMessage());
        return new ResponseException(invalidOperationException.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
