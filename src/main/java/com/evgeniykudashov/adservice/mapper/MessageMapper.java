package com.evgeniykudashov.adservice.mapper;


import com.evgeniykudashov.adservice.mapper.dto.MessageDto;
import com.evgeniykudashov.adservice.mapper.dto.PageDto;
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
    public abstract MessageDto toMessageResponseDto(Message chatMessage);

    public abstract List<MessageDto> toMessageResponseDto(Collection<Message> chatMessageList);

    public abstract PageDto<MessageDto> toPageDto(Page<Message> page);

}
