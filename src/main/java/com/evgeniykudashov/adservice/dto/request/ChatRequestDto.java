package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import java.util.Set;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
public class ChatRequestDto {

    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "chat id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "chat id should be positive")
    })
    long chatId;

    @Positive(message = "advertisement id should be positive",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    Long advertisementId;

    @Positive(message = "user id should be positive",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    Set<Long> userIds;

    public ChatRequestDto(@JsonProperty(required = true) Long advertisementId,
                          @JsonProperty(required = true) Set<Long> userIds) {
        this.advertisementId = advertisementId;
        this.userIds = userIds;
    }

}
