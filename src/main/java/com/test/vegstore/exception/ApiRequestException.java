package com.test.vegstore.exception;

public class ApiRequestException extends IllegalArgumentException {

    public ApiRequestException(String message) {
        super(message);
    }
}
