package com.evgeniykudashov.adservice.controller.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChatService {

    /**
     * chat segment begin
     */
    long create(Chat chat);

    void remove(long chatId);

    Page<Chat> findAllByUserId(long userId, Pageable pageable);

    Chat findById(long chatId);

    /**
     *  chat segment end
     */


    /**
     * chat message segment begin
     */
    void addChatMessage(ChatMessage chatMessage, long chatId);

    void removeChatMessage(ChatMessage chatMessage, long chatId);

    Page<ChatMessage> findChatMessagesByChatId(long chatId, Pageable pageable);

    /**
     * chat message segment end
     */
}
