package com.example.appspringdata;

public class NoSuchMovieExistsException extends RuntimeException {
    private String message;

    public NoSuchMovieExistsException() {}

    public NoSuchMovieExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}