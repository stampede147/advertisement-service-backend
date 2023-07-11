package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.ThingColorDto;
import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ThingColorMapper {

    public abstract ThingColorDto toThingColorDto(ThingColor color);

    public abstract ThingColor toThingColor(ThingColorDto color);
}
