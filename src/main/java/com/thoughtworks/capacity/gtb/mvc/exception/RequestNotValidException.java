package com.thoughtworks.capacity.gtb.mvc.exception;

public class RequestNotValidException extends RuntimeException{
    public RequestNotValidException(String message) {
        super(message);
    }
}
