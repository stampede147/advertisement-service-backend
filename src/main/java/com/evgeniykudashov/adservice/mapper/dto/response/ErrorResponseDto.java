package com.evgeniykudashov.adservice.mapper.dto.response;


import com.evgeniykudashov.adservice.mapper.dto.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {

    private Collection<ErrorDto> errors;
}
