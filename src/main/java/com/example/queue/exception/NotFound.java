package com.example.queue.exception;

public class NotFound extends RuntimeException{
    public NotFound() {
        throw new RuntimeException("Not found");
    }
}