package com.momentum.workout.Exception;

//5xx
public class ExternalServerException extends RuntimeException {
    public ExternalServerException(String message) {
        super(message);
    }
}
