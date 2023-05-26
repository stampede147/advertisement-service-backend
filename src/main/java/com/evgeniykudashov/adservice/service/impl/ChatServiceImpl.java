package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundChatException;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ChatServiceImpl implements ChatService {


    private AdvertisementRepository advertisementRepository;
    private UserRepository userRepository;
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
    public List<Chat> findAllByParticipantUserId(long userId) {
        return chatRepository.findAllByParticipantUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Chat findByParticipantUsersIds(Collection<Long> ids) {
        return chatRepository.findByUserParticipantsIds(ids).orElseThrow(NotFoundChatException::new);
    }


    @Override
    @Transactional
    public void addChatMessage(long chatId, ChatMessage chatMessage) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(NotFoundChatException::new);
        chat.addChatMessage(chatMessage);
        chatRepository.save(chat);
    }

    @Override
    @Transactional
    public void removeChatMessage(long chatId, ChatMessage chatMessage) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(NotFoundChatException::new);
        chat.removeChatMessage(chatMessage);
        chatRepository.save(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessage> findChatMessagesByChatId(long chatId) {
        return chatRepository.findAllChatMessagesByChatId(chatId);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Long, List<ChatMessage>> findChatMessagesByChatIds(Collection<Long> chatIds) {
        return chatRepository.findAllChatMessagesByChatsIds(chatIds);
    }
}
