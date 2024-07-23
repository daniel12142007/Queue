package com.example.queue.exception;

public class NotFoundEmail extends Error {
    public NotFoundEmail() {
        throw new Error("Not found email");
    }
}