package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundChatException;
import com.evgeniykudashov.adservice.exception.NotFoundSuchEntityException;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public long create(long advertisementId, Collection<Long> userIds) {
        return chatRepository.save(new Chat(
                advertisementRepository.findById(advertisementId).orElseThrow(NotFoundSuchEntityException::new),
                userRepository.findAllById(userIds))).getId();
    }

    @Override
    public void remove(long chatId) {
        chatRepository.deleteById(chatId);
    }

    @Override
    public List<Chat> findAllByParticipantUserId(long userId) {
        return chatRepository.findAllByParticipantUserId(userId);
    }

    @Override
    public Chat findByParticipantUsersIds(Collection<Long> ids) {
        return chatRepository.findByUserParticipantsIds(ids).orElseThrow(NotFoundChatException::new);
    }


    @Override
    public void addChatMessage(long chatId, ChatMessage chatMessage) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(NotFoundSuchEntityException::new);
        chat.addChatMessage(chatMessage);
        chatRepository.save(chat);
    }

    @Override
    public void removeChatMessage(long chatId, ChatMessage chatMessage) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(NotFoundSuchEntityException::new);
        chat.removeChatMessage(chatMessage);
        chatRepository.save(chat);
    }

    @Override
    public List<ChatMessage> findChatMessagesByChatId(long chatId) {
        return chatRepository.findAllChatMessagesByChatId(chatId);
    }

    @Override
    public Map<Long, List<ChatMessage>> findChatMessagesByChatIds(Collection<Long> chatIds) {
        return chatRepository.findAllChatMessagesByChatsIds(chatIds);
    }
}
