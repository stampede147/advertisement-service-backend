package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundChatException;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Override
    @Transactional
    public long create(Chat chat) {
        return chatRepository.save(chat).getId();
    }

    @Override
    @Transactional
    public void remove(long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Chat> findAllByUserId(long userId, Pageable pageable) {
        return chatRepository.findAllByUserId(userId, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat findById(long chatId) {
        return chatRepository.findById(chatId).orElseThrow(NotFoundChatException::new);
    }

    @Override
    @Transactional
    public void addChatMessage(ChatMessage chatMessage, long chatId) {
        this.findById(chatId).addChatMessage(chatMessage);
    }

    @Override
    @Transactional
    public void removeChatMessage(ChatMessage chatMessage, long chatId) {
        this.findById(chatId).removeChatMessage(chatMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChatMessage> findChatMessagesByChatId(long chatId, Pageable pageable) {
        return chatRepository.findAllChatMessagesByChatId(chatId, pageable);
    }


}
