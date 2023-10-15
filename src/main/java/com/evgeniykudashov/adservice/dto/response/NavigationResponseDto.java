package com.evgeniykudashov.adservice.dto.response;

import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class NavigationResponseDto {

    private long id;
    private String title;
    private NavigationDto navigation;
    private List<NavigationResponseDto> children;
    private long parentId;

    @Data
    public static class NavigationDto {

        @Positive
        long categoryId;

    }


}
