package com.evgeniykudashov.adservice.controller;

import com.evgeniykudashov.adservice.controller.rest.ReviewController;
import com.evgeniykudashov.adservice.dto.ErrorDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = ReviewController.class)
public class ReviewControllerAdvice {

    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ApiResponse(responseCode = "422", description = "(UNPROCESSABLE ENTITY) Problems with validation: request body contains not exist id")
    public ErrorDto processNotFoundEntityException(NotFoundEntityException e) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setDescription("request body contains not exist id");

        return errorDto;
    }

}
