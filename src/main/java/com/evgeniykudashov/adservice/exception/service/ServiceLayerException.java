package com.evgeniykudashov.adservice.exception.service;

public class ServiceLayerException extends RuntimeException {
    public ServiceLayerException() {
    }

    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceLayerException(Throwable cause) {
        super(cause);
    }

    public ServiceLayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
