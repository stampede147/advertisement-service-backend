package com.evgeniykudashov.adservice.exception.servicelayer;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "not found such entity", code = HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends ServiceLayerException {
}
