package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.chat.ChatMessageDto;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
@Component
public abstract class ChatMessageMapper {

    protected UserRepository userRepository;


    @Mapping(target = "senderId", expression = "java(chatMessage.getSender().getId())")
    public abstract ChatMessageDto toDto(ChatMessage chatMessage);

    public abstract List<ChatMessageDto> toDto(List<ChatMessage> chatMessageList);

    @Mapping(target = "sender", expression = "java(userRepository.getReferenceById(request.getSenderId()))")
    public abstract ChatMessage toEntity(ChatMessageDto request);

    public abstract PageDto<ChatMessageDto> toPageDto(Page<ChatMessage> page);

}
