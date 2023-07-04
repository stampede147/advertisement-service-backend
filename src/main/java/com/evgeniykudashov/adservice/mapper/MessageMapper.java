package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.mapper.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class MessageMapper {

    protected UserRepository userRepository;

    @Mapping(target = "messageId", source = "id")
    @Mapping(target = "chatId", expression = "java(chatMessage.getChat().getId())")
    @Mapping(target = "senderId", expression = "java(chatMessage.getSender().getId())")
    public abstract MessageResponseDto toMessageResponseDto(Message chatMessage);

    public abstract List<MessageResponseDto> toMessageResponseDto(Collection<Message> chatMessageList);

    public abstract PageDto<MessageResponseDto> toPageDto(Page<Message> page);

}
