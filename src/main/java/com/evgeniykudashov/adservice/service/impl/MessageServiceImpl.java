package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.MessageRequestDto;
import com.evgeniykudashov.adservice.dto.response.MessageResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.MessageMapper;
import com.evgeniykudashov.adservice.model.chat.statuses.MessageStatus;
import com.evgeniykudashov.adservice.repository.MessageRepository;
import com.evgeniykudashov.adservice.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper dtoMapper;

    @Override
    @Transactional
    public long createMessage(MessageRequestDto dto) {
        log.trace("Started createMessage(MessageRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        return messageRepository.save(dtoMapper.toMessage(dto, LocalDateTime.now(), MessageStatus.CREATED))
                .getId();
    }

    @Override
    @Transactional
    public void deleteMessage(long messageId) {
        log.trace("Started deleteMessage(long) method");
        log.debug("Provided parameter messageId: {}", messageId);

        messageRepository.deleteById(messageId);
    }

    @Override
    public MessageResponseDto getMessageById(long messageId) {
        log.trace("Started getMessageById(long) method");
        log.debug("Provided parameter messageId: {}", messageId);

        return dtoMapper.toMessageResponseDto(messageRepository
                .findById(messageId)
                .orElseThrow(() -> {
                    NotFoundEntityException exception = new NotFoundEntityException();
                    log.error("Unexpected exception: {}", exception);
                    return exception;
                }));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<MessageResponseDto> getMessagesByChatId(long chatId, Pageable pageable) {
        log.trace("Started getMessagesByChatId(long, Pageable) method");
        log.debug("Provided parameters chatId: {}, pageable: {}", chatId, pageable);

        return dtoMapper.toPageDto(messageRepository.findLastMessages(chatId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<MessageResponseDto> getLastMessageByChatsIds(long[] chatId) {
        log.trace("Started getLastMessageByChatsIds(long[]) method");
        log.debug("Provided parameter chatId[]: {}", chatId);

        return dtoMapper.toMessageResponseDto(messageRepository.findLastMessageByChatId(chatId));
    }
}
