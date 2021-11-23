package com.fitness.fitnessbuddy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// this is created to handle http connections
@ResponseStatus(HttpStatus.NOT_FOUND)
public class InformationNotFoundException extends RuntimeException {
    public InformationNotFoundException(String message) {
        super(message);
    }
}
