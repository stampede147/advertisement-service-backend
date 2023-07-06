package com.evgeniykudashov.adservice.controller;


import com.evgeniykudashov.adservice.mapper.dto.ErrorDto;
import com.evgeniykudashov.adservice.mapper.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class DtoValidationController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto processRSR303Validation(final MethodArgumentNotValidException e) {
        return new ErrorResponseDto(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ErrorDto.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toSet()));
    }

}
