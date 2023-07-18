package com.evgeniykudashov.adservice.exception.servicelayer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException.NOT_FOUND_MESSAGE;

@ResponseStatus(reason = NOT_FOUND_MESSAGE, code = HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends ServiceLayerException {

    protected static final String NOT_FOUND_MESSAGE = "Not found such entity";

    public NotFoundEntityException() {
        this(NOT_FOUND_MESSAGE);
    }

    public NotFoundEntityException(String message) {
        super(message);
    }

    public NotFoundEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundEntityException(Throwable cause) {
        super(cause);
    }

    public NotFoundEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
