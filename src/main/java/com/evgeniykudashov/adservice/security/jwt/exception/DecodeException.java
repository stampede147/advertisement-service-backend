package com.evgeniykudashov.adservice.security.jwt.exception;

public class DecodeException extends RuntimeException {

    public DecodeException(String message) {
        super(message);
    }
}
