package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.response.CategoryResponseDto;
import com.evgeniykudashov.adservice.model.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {


    @Mapping(target = "parentId", source = "parent.id")
    public abstract CategoryResponseDto toResponseDto(Category category);

}
