package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.EditLayoutFieldResponseDto;
import com.evgeniykudashov.adservice.model.advertisementEdit.EditLayoutField;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public abstract class EditLayoutFieldMapper {

    @Mapping(target = "viewConfig.size", source = "viewConfig.size")
    @Mapping(target = "viewConfig.type", source = "viewConfig.type")
    public abstract EditLayoutFieldResponseDto toResponseDto(EditLayoutField field);

    public abstract List<EditLayoutFieldResponseDto> toResponseDto(List<EditLayoutField> field);

}
