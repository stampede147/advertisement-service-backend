package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.ShoeSizeDto;
import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ShoeSizeMapper {

    public abstract ShoeSize toShoeSize(ShoeSizeDto dto);

    @InheritInverseConfiguration
    public abstract ShoeSizeDto toShoeSizeDto(ShoeSize size);


}
