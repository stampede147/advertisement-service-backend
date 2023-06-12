package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.Chat;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.impl.ChatServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    ChatRepository chatRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    AdvertisementRepository advertisementRepository;
    @InjectMocks
    ChatServiceImpl sut;


    @Test
    void create_chat() {
        Chat chat = TestValues.getChatObject();
        Mockito.when(chatRepository.save(Mockito.any(Chat.class))).thenReturn(chat);

        sut.create(chat);

        Mockito.verify(chatRepository).save(chat);
    }

    @Test
    void remove() {

        sut.remove(TestValues.ID);

        Mockito.verify(chatRepository).deleteById(Mockito.any(Long.class));
    }

    @Test
    void addChatMessage() {
        Chat chat = TestValues.getChatObject();
        ChatMessage chatMessage = TestValues.getChatMessageObject();
        Mockito.when(chatRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(chat));

        sut.addChatMessage(chatMessage, chat.getId());

        Mockito.verify(chatRepository).save(chat);
        Assertions.assertTrue(chat.getChatMessages().contains(chatMessage));
    }

    @Test
    void removeChatMessage() {
        Chat chat = TestValues.getChatObject();
        ChatMessage chatMessage = TestValues.getChatMessageObject();
        Mockito.when(chatRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(chat));

        sut.removeChatMessage(chatMessage, chat.getId());

        Mockito.verify(chatRepository).save(chat);
        Assertions.assertFalse(chat.getChatMessages().contains(chatMessage));
    }

    @Test
    void findAllByParticipantUserId() {
        Long id = TestValues.ID;

        List<Chat> result = sut.findAllByParticipantUserId(id);

        Mockito.verify(chatRepository).findAllByParticipantUserId(Mockito.any(Long.class));
    }

    @Test
    void findByParticipantUsersIds() {
        List<Long> userIds = List.of(1L);
        Chat chat = TestValues.getChatObject();
        Mockito.when(chatRepository.findByUserParticipantsIds(userIds)).thenReturn(Optional.of(chat));

        sut.findByParticipantUsersIds(userIds);

        Mockito.verify(chatRepository).findByUserParticipantsIds(userIds);

    }


    @Test
    void find_chat_messages_by_chat_id() {
        Chat chat = TestValues.getChatObject();

        sut.findChatMessagesByChatId(chat.getId());

        Mockito.verify(chatRepository).findAllChatMessagesByChatId(Mockito.any(Long.class));
    }

}