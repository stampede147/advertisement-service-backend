package com.evgeniykudashov.adservice.model.advertisement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EnumEntity {

    int getId();


    String getLabel();

    @JsonCreator
    EnumEntity valueForId(int id);
}
