package com.evgeniykudashov.adservice.dto.response;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LayoutResponseDto {

    private FieldResponseDto[] fields;

    private StepResponseDto[] steps;

}
