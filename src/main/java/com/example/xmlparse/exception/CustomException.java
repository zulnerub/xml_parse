package com.example.xmlparse.exception;

/**
 * Custom runtime exception to handle rethrow caught checked exceptions.
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
