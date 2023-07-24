package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MessageControllerUnitTest {

    @Mock
    MessageService messageService;

    @InjectMocks
    MessageController sut;

    @Test
    void createMessage() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
        // Arrange
        MessageRequestDto dto = Mockito.mock(MessageRequestDto.class);

        // Act
        ResponseEntity<?> message = sut.createMessage(dto);

        // Assert
        Assertions.assertNotNull(message);
        Mockito.verify(messageService).createMessage(dto);

        RequestContextHolder.resetRequestAttributes();

    }

    @Test
    void deleteMessage() {
        long id = 1L;

        sut.deleteMessage(id);

        Mockito.verify(messageService).deleteMessage(id);
    }

    @Test
    void getMessageById() {
        long id = 1L;

        ResponseEntity<MessageResponseDto> messageById = sut.getMessageById(id);

        Assertions.assertNotNull(messageById);
        Mockito.verify(messageService).getMessageById(id);
    }

    @Test
    void getLastMessage() {

        long[] IDs = {1L};
        MessageResponseDto mock = Mockito.mock(MessageResponseDto.class);
        Collection<MessageResponseDto> messagesResponseBody = List.of(mock);
        Mockito.when(messageService.getLastMessageByChatsIds(Mockito.any())).thenReturn(messagesResponseBody);


        ResponseEntity<Collection<MessageResponseDto>> result = sut.getLastMessage(IDs);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(messagesResponseBody, result.getBody());
    }

}