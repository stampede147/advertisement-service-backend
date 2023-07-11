package com.evgeniykudashov.adservice.exception;


import com.evgeniykudashov.adservice.exception.service.ServiceLayerException;

public class InvalidIdException extends ServiceLayerException {
    public InvalidIdException(String providedIdIsWrong) {
        super(providedIdIsWrong);
    }
}
