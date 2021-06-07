package com.challenge.exceptions;

import com.challenge.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyFollowing.class)
    public ResponseEntity<ErrorResponse> handleAlreadyFollowing(AlreadyFollowing alreadyFollowing) {
        ErrorResponse response = buildResponse(alreadyFollowing.getMessage(), alreadyFollowing.getStackTrace());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(SelfFollow.class)
    public ResponseEntity<ErrorResponse> handleSelfFollow(SelfFollow selfFollow) {
        ErrorResponse response = buildResponse(selfFollow.getMessage(), selfFollow.getStackTrace());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(SellerNotFound.class)
    public ResponseEntity<ErrorResponse> handleSellerNotFound(SellerNotFound sellerNotFound) {
        ErrorResponse response = buildResponse(sellerNotFound.getMessage(), sellerNotFound.getStackTrace());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFound userNotFound) {
        ErrorResponse response = buildResponse(userNotFound.getMessage(), userNotFound.getStackTrace());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private ErrorResponse buildResponse(String message, StackTraceElement[] trace) {
        return ErrorResponse.builder()
                .message(message)
                .trace(Arrays.stream(trace).map(element -> element.getFileName() + ":" + element.getLineNumber()).collect(Collectors.toList()))
                .build();
    }
}
