package com.evgeniykudashov.adservice.exception.servicelayer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "not found refresh token", code = HttpStatus.NOT_FOUND)
public class NotFoundRefreshTokenException extends NotFoundEntityException {

    public NotFoundRefreshTokenException() {
    }

    public NotFoundRefreshTokenException(String message) {
        super(message);
    }

    public NotFoundRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundRefreshTokenException(Throwable cause) {
        super(cause);
    }

    public NotFoundRefreshTokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
