package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.view.dto.AdvertisementDto;
import com.evgeniykudashov.adservice.view.dto.ChatDto;
import com.evgeniykudashov.adservice.view.dto.ChatMessageDto;
import com.evgeniykudashov.adservice.view.dto.UserDto;

import java.util.List;
import java.util.Map;

public interface ChatService {

    Chat createChat(AdvertisementDto advertisementDto, List<UserDto> participants);

    void removeChat(ChatDto chat);

    List<ChatDto> findChatsByParticipantUser(UserDto participant);

    ChatDto findChatByParticipantsUsers(List<UserDto> participants);

    ChatMessageDto createChatMessage(ChatDto chat, ChatMessageDto chatMessage);

    void removeChatMessage(ChatMessageDto message);

    List<ChatMessageDto> findChatMessagesByChat(Chat chat);

    Map<ChatDto, List<ChatMessageDto>> findChatMessagesByChats(List<ChatDto> chats);
}
