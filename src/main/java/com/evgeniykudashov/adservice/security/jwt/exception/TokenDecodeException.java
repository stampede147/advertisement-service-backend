package com.evgeniykudashov.adservice.security.jwt.exception;


import com.evgeniykudashov.adservice.exception.servicelayer.ServiceLayerException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = TokenDecodeException.EXCEPTION_MESSAGE)
public class TokenDecodeException extends ServiceLayerException {

    protected final static String EXCEPTION_MESSAGE = "Problems with decoding token";

    public TokenDecodeException(String message) {
        super(message, null);
    }

    public TokenDecodeException(Throwable cause) {
        super(EXCEPTION_MESSAGE, cause);
    }

    public TokenDecodeException(String message, Throwable cause) {
        super(message, cause);
    }
}
