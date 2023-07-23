package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.MessageMapper;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.repository.MessageRepository;
import com.evgeniykudashov.adservice.service.impl.MessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {


    @Mock
    MessageRepository messageRepository;

    @Mock
    MessageMapper mapper;

    @InjectMocks
    MessageServiceImpl sut;

    @Test
    void createMessage() {
        MessageRequestDto dto = new MessageRequestDto();
        Message mappedEntity = new Message();
        Mockito.when(mapper.toMessage(Mockito.eq(dto), Mockito.any(), Mockito.any()))
                .thenReturn(mappedEntity);

        Message savedMessage = new Message();
        savedMessage.setId(1L);
        Mockito.when(messageRepository.save(mappedEntity)).thenReturn(savedMessage);

        //act
        long savedMessageId = sut.createMessage(dto);

        Assertions.assertEquals(savedMessage.getId(), savedMessageId);

    }

    @Test
    void deleteMessage() {
        final long ID = TestObjects.SAVED_ENTITY_ID;

        sut.deleteMessage(ID);

        Mockito.verify(messageRepository).deleteById(ID);
    }

    @Test
    void getMessageById() {
        final long ID = TestObjects.SAVED_ENTITY_ID;
        Message savedMessage = new Message();
        savedMessage.setId(ID);
        Mockito.when(messageRepository.findById(ID)).thenReturn(Optional.of(savedMessage));

        MessageResponseDto mappedSavedMessage = new MessageResponseDto();
        Mockito.when(mapper.toMessageResponseDto(savedMessage)).thenReturn(mappedSavedMessage);


        MessageResponseDto result = sut.getMessageById(ID);


        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, mappedSavedMessage);
    }

    @Test
    void getMessagesByChatId() {
        long chatId = TestObjects.SAVED_ENTITY_ID;
        Pageable pageable = Mockito.mock(Pageable.class);
        Page page = Mockito.mock(Page.class);
        Mockito.when(messageRepository.findLastMessages(chatId, pageable)).thenReturn(page);

        PageDto<Object> pageDto = Mockito.mock(PageDto.class);
        Mockito.when(mapper.toPageDto(page)).thenReturn(pageDto);

        //act
        PageDto<MessageResponseDto> result = sut.getMessagesByChatId(chatId, pageable);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(pageDto, result);
    }

    @Test
    void getLastMessageByChatsIds() {
        long[] chatIds = {1};

        List<Message> value = List.of(Mockito.mock(Message.class));
        Mockito.when(messageRepository.findLastMessageByChatId(chatIds)).thenReturn(value);

        List<MessageResponseDto> lastMessages = List.of(Mockito.mock(MessageResponseDto.class));
        Mockito.when(mapper.toMessageResponseDto(value))
                .thenReturn(lastMessages);

        //act
        Collection<MessageResponseDto> result = sut.getLastMessageByChatsIds(chatIds);

        //assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(chatIds.length, result.size());
        Assertions.assertEquals(lastMessages, result);
    }
}