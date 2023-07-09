package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class ChatMapper {

    protected AdvertisementRepository advertisementRepository;
    protected UserRepository userRepository;

    @Mapping(target = "id", expression = "java(dto.getChatId())")
    @Mapping(target = "advertisement", expression = "java(advertisementRepository.getReferenceById(dto.getAdvertisementId()))")
    @Mapping(target = "createdAt", expression = "java(createdAt)")
    @Mapping(target = "participants", source = "dto.userIds", qualifiedByName = "usersIdsToUsers")
    public abstract Chat toChat(ChatRequestDto dto, LocalDate createdAt);

    @Named("usersIdsToUsers")
    protected Set<User> usersIdsToUsers(Set<Long> usersIds) {
        return usersIds.stream()
                .map(userId -> userRepository.getReferenceById(userId))
                .collect(Collectors.toSet());
    }

    @Mapping(target = "chatId", ignore = true)
    public abstract ChatResponseDto toChatResponseDto(Chat chat);


    public abstract PageDto<ChatResponseDto> toPageDto(Page<Chat> page);


}
