package com.evgeniykudashov.adservice.model.advertisementEdit;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewType {

    IMAGE_UPLOADER("imageUploader"),
    DESCRIPTION("description"),
    HIDDEN("hidden"),
    PRICE("price");


    @JsonValue
    private final String name;

}
