package com.evgeniykudashov.adservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "not found such advertisement", code = HttpStatus.NOT_FOUND)
public class NotFoundAdvertisementException extends NotFoundEntityException {
}
