package com.example.appspringdata;

public class MovieAlreadyExistsException extends RuntimeException {
    private String message;

    public MovieAlreadyExistsException() {}

    public MovieAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
