package com.evgeniykudashov.adservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LayoutRequestDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    private class KeyValueRequestDto {
        private String key;
        private long id;
    }

    private KeyValueRequestDto[] keyValues;

    private long advertisementId;

    private long categoryId;
}
