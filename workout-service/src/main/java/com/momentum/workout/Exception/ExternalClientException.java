package com.momentum.workout.Exception;

//4xx
public class ExternalClientException extends RuntimeException {
    public ExternalClientException(String message) {
        super(message);
    }
}
