package com.evgeniykudashov.adservice.mapper.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateChatRequestDto {

    @JsonProperty(required = true)
    @NonNull
    Long advertisementId;

    @JsonProperty(required = true)
    Set<Long> userIds;


}
