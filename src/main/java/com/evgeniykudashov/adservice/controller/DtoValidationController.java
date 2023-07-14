package com.evgeniykudashov.adservice.controller;


import com.evgeniykudashov.adservice.dto.ErrorDto;
import com.evgeniykudashov.adservice.dto.response.ErrorResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.InvalidIdException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "422", description = "(UNPROCESSABLE ENTITY) Problems with validation: request body contains invalid fields.")
    public ErrorResponseDto processJSR303Validation(final MethodArgumentNotValidException e) {
        return new ErrorResponseDto(e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> ErrorDto.of(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toSet()));
    }

    @Hidden
    @ExceptionHandler(InvalidIdException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<?, ?> invalidId(InvalidIdException e) {
        return Map.of("error: ", e.getMessage());
    }

}
