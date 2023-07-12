package com.evgeniykudashov.adservice.controller;


import com.evgeniykudashov.adservice.dto.ErrorDto;
import com.evgeniykudashov.adservice.dto.response.ErrorResponseDto;
import com.evgeniykudashov.adservice.exception.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DtoValidationController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponseDto processJSR303Validation(final MethodArgumentNotValidException e) {
        return new ErrorResponseDto(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ErrorDto.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toSet()));
    }

    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<?, ?> invalidId(InvalidIdException e) {
        return Map.of("error: ", e.getMessage());
    }

}
