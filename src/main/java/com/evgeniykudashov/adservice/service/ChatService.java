package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface ChatService {

    long create(long advertisementId, Collection<Long> usersIds);

    void remove(long chatId);

    List<Chat> findAllByParticipantUserId(long userId);

    Chat findByParticipantUsersIds(Collection<Long> participantUsersIds);

    void addChatMessage(long chatId, ChatMessage chatMessage);

    void removeChatMessage(long chatId, ChatMessage chatMessage);

    List<ChatMessage> findChatMessagesByChatId(long chatId);

    Map<Long, List<ChatMessage>> findChatMessagesByChatIds(Collection<Long> chatIds);
}
