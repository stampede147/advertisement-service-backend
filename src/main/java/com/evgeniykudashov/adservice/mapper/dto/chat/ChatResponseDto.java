package com.evgeniykudashov.adservice.mapper.dto.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;


@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class ChatResponseDto {

    long chatId;

    long advertisementId;

    Set<Long> userIds;

    List<ChatMessageDto> chatMessages;


}
