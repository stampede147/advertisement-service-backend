package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.StepFieldResponseDto;
import com.evgeniykudashov.adservice.dto.response.StepResponseDto;
import com.evgeniykudashov.adservice.model.field.Field;
import com.evgeniykudashov.adservice.model.step.Step;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.Qualifier;

import java.util.List;

@Mapper(componentModel = "spring", imports = Field.class)
public abstract class StepMapper {

    @Mapping(target = "children", source = "children", qualifiedByName = "toResponseDto_children_mapping")
    public abstract StepResponseDto toResponseDto(Step step);

    public abstract StepResponseDto[] toResponseDto(List<Step> step);

    @Named("toResponseDto_children_mapping")
    public StepFieldResponseDto[] stepChildrenMapping(List<Field> fields){
        return fields.stream()
                .map(field -> new StepFieldResponseDto(field.getId()))
                .toArray(StepFieldResponseDto[]::new);
    }


}
