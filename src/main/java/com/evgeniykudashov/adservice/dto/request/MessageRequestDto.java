package com.evgeniykudashov.adservice.dto.request;


import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import com.evgeniykudashov.adservice.validation.UpdateConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {


    @Range.List(value = {
            @Range(min = 0, max = 0, groups = CreateConstraint.class, message = "chat id should be zero"),
            @Range(min = 1, groups = UpdateConstraint.class, message = "chat id should be positive")
    })
    long messageId;

    Long chatId;

    @NotEmpty(message = "body should not be empty",
            groups = {CreateConstraint.class, UpdateConstraint.class})
    String body;
}
