package com.evgeniykudashov.adservice.dto.response;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Collections;

@Data
@FieldDefaults(level = AccessLevel.PUBLIC)
public class PageDto<T> {

    Collection<T> content;

    int totalPages;

    long totalElements;

    int size;

    int number;

    @JsonGetter
    @JsonInclude
    public Collection<T> getContent() {
        return content == null ? Collections.emptyList()
                : content;


    }
}
