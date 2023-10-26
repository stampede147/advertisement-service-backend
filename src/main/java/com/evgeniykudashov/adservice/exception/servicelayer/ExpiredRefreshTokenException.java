package com.evgeniykudashov.adservice.exception.servicelayer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "(401) Refresh token is expired")
public class ExpiredRefreshTokenException extends RuntimeException {
}
