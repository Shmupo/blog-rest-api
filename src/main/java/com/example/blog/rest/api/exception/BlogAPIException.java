package com.example.blog.rest.api.exception;

// this handles exceptions where a comment does not belong to a post

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BlogAPIException(String customMessage, HttpStatus status, String message) {
        super(customMessage);
        this.status = status;
        this.message = message;
    }
}
