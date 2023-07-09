package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class MessageMapper {

    protected UserRepository userRepository;
    protected ChatRepository chatRepository;

    @Mapping(target = "id", expression = "java(dto.getMessageId())")
    @Mapping(target = "chat", expression = "java(chatRepository.getReferenceById(dto.getChatId()))")
    @Mapping(target = "sender", expression = "java(userRepository.getReferenceById(dto.getSenderId()))")
    @Mapping(target = "status", expression = "java(status)")
    @Mapping(target = "createdAt", expression = "java(createdAt)")
    public abstract Message toMessage(MessageRequestDto dto,
                                      LocalDateTime createdAt,
                                      MessageStatus status);

    @Mapping(target = "messageId", source = "id")
    @Mapping(target = "chatId", expression = "java(chatMessage.getChat().getId())")
    @Mapping(target = "senderId", expression = "java(chatMessage.getSender().getId())")
    public abstract MessageResponseDto toMessageResponseDto(Message chatMessage);

    public abstract List<MessageResponseDto> toMessageResponseDto(Collection<Message> chatMessageList);

    public abstract PageDto<MessageResponseDto> toPageDto(Page<Message> page);

}
