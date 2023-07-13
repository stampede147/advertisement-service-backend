package com.evgeniykudashov.adservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDto {
    private String field;
    private String description;

    private ErrorDto(String field, String message) {
        this.field = field;
        this.description = message;
    }

    public static ErrorDto of(String field, String message) {
        return new ErrorDto(field, message);
    }
}
