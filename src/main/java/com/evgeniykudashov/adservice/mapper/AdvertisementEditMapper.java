package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.response.EditLayoutResponseDto;
import com.evgeniykudashov.adservice.dto.response.NavigationResponseDto;
import com.evgeniykudashov.adservice.model.advertisementEdit.EditLayoutStep;
import com.evgeniykudashov.adservice.model.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {EditLayoutStepMapper.class})
public abstract class AdvertisementEditMapper {

    @Mapping(target = "navigation.categoryId", source = "category.id")
    @Mapping(target = "parentId", source = "parent.id")
    public abstract NavigationResponseDto toResponseDto(Category category);


    /**
     * @param dummy dummy property to prevent Mapstruct complaining
     *              "Can't generate mapping method from iterable type to non-iterable type."
     * @param steps the steps
     */
    @Mapping(target = "steps", source = "steps")
    public abstract EditLayoutResponseDto toResponseDto(Void dummy, List<EditLayoutStep> steps);

}
