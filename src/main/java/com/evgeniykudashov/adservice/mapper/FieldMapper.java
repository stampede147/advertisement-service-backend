package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.FieldResponseDto;
import com.evgeniykudashov.adservice.model.field.Field;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class FieldMapper {

    public abstract FieldResponseDto toResponseDto(Field field);

    public abstract FieldResponseDto[] toResponseDto(List<Field> fields);
}
