package com.evgeniykudashov.adservice.exception.servicelayer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidIdException extends ServiceLayerException {
    public InvalidIdException(String providedIdIsWrong) {
        super(providedIdIsWrong);
    }
}
