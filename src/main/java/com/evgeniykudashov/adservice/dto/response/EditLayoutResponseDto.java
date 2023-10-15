package com.evgeniykudashov.adservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class EditLayoutResponseDto {

    @JsonInclude(JsonInclude.Include.ALWAYS)
    public List<EditLayoutStepResponseDto> steps;
}
