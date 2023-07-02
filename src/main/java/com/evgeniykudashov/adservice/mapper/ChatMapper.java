package com.evgeniykudashov.adservice.mapper;

import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.mapper.dto.response.ChatResponseDto;
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

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Setter(onMethod_ = @Autowired)
public abstract class ChatMapper {

    protected AdvertisementRepository advertisementRepository;
    protected UserRepository userRepository;

    @Mapping(target = "chatId", ignore = true)
    public abstract ChatResponseDto toChatResponseDto(Chat chat);

    @Named("usersToUserIds")
    protected Set<Long> usersToUserIds(Set<User> users) {
        return users.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

    public abstract PageDto<ChatResponseDto> toPageDto(Page<Chat> page);


}
