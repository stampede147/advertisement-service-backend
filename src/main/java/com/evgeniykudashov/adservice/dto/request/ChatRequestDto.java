package com.evgeniykudashov.adservice.dto.request;

import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;


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

    @Parameter(description = "user, who requested to create chat")
    @NotNull(message = "user id should be positive",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    Long userId;

    public ChatRequestDto(@JsonProperty(required = true) long chatId,
                          @JsonProperty(required = true) Long advertisementId,
                          @JsonProperty(required = true) Long userId) {
        this.chatId = chatId;
        this.advertisementId = advertisementId;
        this.userId = userId;
    }
}
