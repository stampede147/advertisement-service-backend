package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.EditLayoutStepResponseDto;
import com.evgeniykudashov.adservice.model.EditLayoutStep;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = EditLayoutFieldMapper.class)
public abstract class EditLayoutStepMapper {

    public abstract EditLayoutStepResponseDto toResponseDto(EditLayoutStep step);

    public abstract List<EditLayoutStepResponseDto> toResponseDto(List<EditLayoutStep> step);
}
