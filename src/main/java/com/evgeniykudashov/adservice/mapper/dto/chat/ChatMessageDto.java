package com.evgeniykudashov.adservice.mapper.dto.chat;


import com.evgeniykudashov.adservice.model.domain.aggregate.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.MessageBody;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.PublicationDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC)
@Data
public class ChatMessageDto {


    Long chatId;
    MessageBody body;
    Long senderId;
    MessageStatus messageStatus;
    PublicationDateTime publicationDateTime;
}
