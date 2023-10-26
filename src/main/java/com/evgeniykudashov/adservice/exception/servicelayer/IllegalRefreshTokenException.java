package com.evgeniykudashov.adservice.exception.servicelayer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "(400) Invalid refresh token provided")
public class IllegalRefreshTokenException extends IllegalArgumentException {
    public IllegalRefreshTokenException() {
    }

    public IllegalRefreshTokenException(String s) {
        super(s);
    }

    public IllegalRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
