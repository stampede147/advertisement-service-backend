package com.evgeniykudashov.adservice.mapper.internalmapping;

public interface EntityMapperHandler<ET, ES, T> {

    void toEntity(ET target, ES source);

    void toDto(T target, ET source);
}
