package com.bootcamp.challenge.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseException {

    private final String error;
}
