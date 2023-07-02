package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.MessageMapper;
import com.evgeniykudashov.adservice.mapper.dto.MessageDto;
import com.evgeniykudashov.adservice.mapper.dto.PageDto;
import com.evgeniykudashov.adservice.model.chat.Message;
import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.MessageRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class MessageServiceImpl implements MessageService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final MessageMapper dtoMapper;

    @Override
    @Transactional
    public long createMessage(MessageDto dto) {
        return messageRepository.save(Message.builder()
                        .id(0L)
                        .body(dto.getBody())
                        .sender(userRepository.getReferenceById(dto.getSenderId()))
                        .chat(chatRepository.getReferenceById(dto.getChatId()))
                        .createdAt(LocalDateTime.now())
                        .status(MessageStatus.CREATED)
                        .build())
                .getId();
    }

    @Override
    @Transactional
    public void deleteMessage(long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public MessageDto getMessageById(long messageId) {
        return dtoMapper.toMessageResponseDto(findMessageById(messageId));
    }

    private Message findMessageById(long messageId) {
        return messageRepository.findById(messageId).orElseThrow(NotFoundEntityException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<MessageDto> getLastMessagesByChatId(long chatId, Pageable pageable) {
        return dtoMapper.toPageDto(messageRepository.findLastMessages(chatId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<MessageDto> getLastMessageByChatsIds(long[] chatId) {
        return dtoMapper.toMessageResponseDto(messageRepository.findLastMessageByChatId(chatId));
    }
}
