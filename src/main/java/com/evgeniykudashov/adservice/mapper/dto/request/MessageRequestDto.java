package com.evgeniykudashov.adservice.mapper.dto.request;


import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageRequestDto {

    long messageId;

    long chatId;

    String body;

    long senderId;

    MessageStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime createdAt;
}
