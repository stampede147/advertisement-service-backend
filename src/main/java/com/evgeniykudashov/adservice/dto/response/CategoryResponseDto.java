package com.evgeniykudashov.adservice.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class CategoryResponseDto {

    private long id;

    private String title;

    private List<CategoryResponseDto> children;

    private long parentId;
}
