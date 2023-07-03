package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class ChatMapper {

    protected AdvertisementRepository advertisementRepository;
    protected UserRepository userRepository;

    @Mapping(target = "chatId", ignore = true)
    public abstract ChatResponseDto toChatResponseDto(Chat chat);


    public abstract PageDto<ChatResponseDto> toPageDto(Page<Chat> page);


}
