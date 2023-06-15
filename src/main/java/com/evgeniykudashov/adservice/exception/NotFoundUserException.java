package com.evgeniykudashov.adservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "not found such user", code = HttpStatus.NOT_FOUND)
public class NotFoundUserException extends NotFoundEntityException {
}
