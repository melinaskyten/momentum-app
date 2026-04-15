package com.momentum.workout.exception;

public class ExternalServerException extends RuntimeException {
    public ExternalServerException(String message) {
        super(message);
    }
}