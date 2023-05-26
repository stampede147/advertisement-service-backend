package com.evgeniykudashov.adservice.model.domain.aggregate.chat;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.chat.valueobject.ChatMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class ChatTest {

    @Mock
    List<ChatMessage> messages;

    @InjectMocks
    Chat sut;

    @Test
    void can_add_chat_message() {
        ChatMessage chatMessage = TestValues.getChatMessageObject();

        sut.addChatMessage(chatMessage);

        Mockito.verify(messages).add(chatMessage);
    }

    @Test
    void can_remove_chat_message() {
        ChatMessage chatMessage = TestValues.getChatMessageObject();

        sut.removeChatMessage(chatMessage);

        Mockito.verify(messages).remove(chatMessage);
    }

}