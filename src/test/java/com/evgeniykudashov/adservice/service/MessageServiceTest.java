package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.MessageMapper;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {


    @Mock
    MessageRepository messageRepository;

    @Mock
    MessageMapper mapper;

    @InjectMocks
    MessageServiceImpl sut;

    @Test
    void createMessage_should_return_message_id_when_created_successfully() {
        // Arrange
        MessageRequestDto requestDto = new MessageRequestDto();
        Message message = Mockito.mock(Message.class);

        Mockito.when(message.getId())
                .thenReturn(1L);
        Mockito.when(mapper.toMessage(Mockito.any(MessageRequestDto.class), Mockito.any(LocalDateTime.class), Mockito.any(MessageStatus.class)))
                .thenReturn(message);
        Mockito.when(messageRepository.save(Mockito.any(Message.class)))
                .thenReturn(message);

        // Act
        long messageId = sut.createMessage(requestDto);

        // Assert
        Assertions.assertEquals(1L, messageId);
        Mockito.verify(mapper).toMessage(Mockito.eq(requestDto), Mockito.any(), Mockito.eq(MessageStatus.CREATED));
    }

    @Test
    void deleteMessage_should_invoke_repository_delete_by_id() {
        // Arrange
        long messageId = 1L;

        // Act
        sut.deleteMessage(messageId);

        // Assert
        Mockito.verify(messageRepository).deleteById(messageId);
    }

    @Test
    void getMessageById_should_return_message_response_dto_when_message_found() {
        // Arrange
        long messageId = 1L;
        Message message = new Message();
        message.setId(messageId);
        MessageResponseDto responseDto = new MessageResponseDto();
        responseDto.setMessageId(messageId);

        when(messageRepository.findById(messageId)).thenReturn(Optional.of(message));
        when(mapper.toMessageResponseDto(message)).thenReturn(responseDto);

        // Act
        MessageResponseDto resultDto = sut.getMessageById(messageId);

        // Assert
        Assertions.assertNotNull(resultDto);
        Assertions.assertEquals(messageId, resultDto.getMessageId());
        Mockito.verify(messageRepository).findById(messageId);
        Mockito.verify(mapper).toMessageResponseDto(message);
    }

    @Test
    void getMessageById_should_throw_NotFoundEntityException_when_message_not_found() {
        // Arrange
        long messageId = 1L;

        when(messageRepository.findById(messageId)).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NotFoundEntityException.class, () -> sut.getMessageById(messageId));
        Mockito.verify(messageRepository).findById(messageId);
        Mockito.verify(mapper, Mockito.never()).toMessageResponseDto(Mockito.any(Message.class));
    }

    @Test
    void getMessagesByChatId_should_return_page_dto_of_message_response_dto() {
        // Arrange
        long chatId = TestObjects.SAVED_ENTITY_ID;
        Page<Message> page = Mockito.mock(Page.class);
        Pageable pageable = Mockito.mock(Pageable.class);
        PageDto pageDto = Mockito.mock(PageDto.class);

        Mockito.when(messageRepository.findLastMessages(chatId, pageable))
                .thenReturn(page);
        Mockito.when(pageDto.getTotalElements())
                .thenReturn(1L);
        Mockito.when(mapper.toPageDto(page))
                .thenReturn(pageDto);

        // Act
        PageDto<MessageResponseDto> resultPageDto = sut.getMessagesByChatId(chatId, pageable);

        // Assert
        Assertions.assertNotNull(resultPageDto);
        Assertions.assertEquals(1, resultPageDto.getTotalElements());
    }

    @Test
    void getLastMessageByChatsIds_should_return_collection_of_message_response_dto() {
        // Arrange
        long[] chatIds = {1L, 2L};
        List<Message> messageEntities = Arrays.stream(chatIds)
                .mapToObj(id -> Mockito.mock(Message.class))
                .collect(Collectors.toList());
        List<MessageResponseDto> messageResponseDtos = Arrays.stream(chatIds)
                .mapToObj(id -> Mockito.mock(MessageResponseDto.class))
                .collect(Collectors.toList());

        Mockito.when(messageRepository.findLastMessageByChatId(chatIds))
                .thenReturn(messageEntities);
        Mockito.when(mapper.toMessageResponseDto(messageEntities))
                .thenReturn(messageResponseDtos);

        // Act
        Collection<MessageResponseDto> result = sut.getLastMessageByChatsIds(chatIds);

        // Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
    }
}