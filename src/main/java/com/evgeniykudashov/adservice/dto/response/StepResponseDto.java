package com.evgeniykudashov.adservice.dto.response;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StepResponseDto {

    private Long id;

    private String title;

    private long order;

    private StepFieldResponseDto[] children;

}
