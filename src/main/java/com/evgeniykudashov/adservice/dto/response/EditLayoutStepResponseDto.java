package com.evgeniykudashov.adservice.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class EditLayoutStepResponseDto {

    public long id;

    public String title;

    public long position;

    public List<EditLayoutFieldResponseDto> fields;

}
