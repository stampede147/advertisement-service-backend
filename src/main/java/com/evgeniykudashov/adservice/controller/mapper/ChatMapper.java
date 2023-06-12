package com.evgeniykudashov.adservice.controller.mapper;

import com.evgeniykudashov.adservice.controller.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.controller.mapper.dto.chat.ChatResponseDto;
import com.evgeniykudashov.adservice.controller.mapper.dto.chat.CreateChatRequestDto;
import com.evgeniykudashov.adservice.controller.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.controller.repository.UserRepository;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = ChatMessageMapper.class)
@Setter(onMethod_ = @Autowired)
public abstract class ChatMapper {

    protected AdvertisementRepository advertisementRepository;
    protected UserRepository userRepository;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "participants", source = "userIds", qualifiedByName = "userIdsToUsers")
    @Mapping(target = "advertisement", expression = "java(advertisementRepository.getReferenceById(requestDto.getAdvertisementId()))")
    public abstract Chat ToChat(CreateChatRequestDto requestDto);

    @Named("userIdsToUsers")
    protected Set<User> userIdsToUsers(Set<Long> userIds) {
        return userIds.stream()
                .map(id -> userRepository.getReferenceById(id))
                .collect(Collectors.toSet());
    }


    @Mappings(value = {
            @Mapping(target = "userIds", source = "participants", qualifiedByName = "usersToUserIds"),
            @Mapping(target = "chatId", expression = "java(chat.getId())"),
            @Mapping(target = "advertisementId", expression = "java(chat.getAdvertisement().getId())")
    })
    public abstract ChatResponseDto toChatResponseDto(Chat chat);

    @Named("usersToUserIds")
    protected Set<Long> usersToUserIds(Set<User> users) {
        return users.stream()
                .map(user -> user.getId())
                .collect(Collectors.toSet());
    }

    public abstract PageDto<ChatResponseDto> toPageDto(Page<Chat> page);


}
