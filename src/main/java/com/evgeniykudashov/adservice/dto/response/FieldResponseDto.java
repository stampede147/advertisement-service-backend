package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.model.field.FieldType;
import com.evgeniykudashov.adservice.model.step.Step;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FieldResponseDto {

    private long id;

    private String name;

    private String datatype;

    private String label;

    private long stepId;

    private long order;

    @Enumerated(value = EnumType.STRING)
    private FieldType type;

}
