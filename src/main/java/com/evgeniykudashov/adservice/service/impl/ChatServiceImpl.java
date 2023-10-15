package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.ChatMapper;
import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.chat.ChatStatus;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Objects;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    private final AdvertisementRepository advertisementRepository;

    private final UserRepository userRepository;

    private final ChatMapper dtoMapper;

    private final Converter<Principal, Long> principalConverter;

    @Override
    @Transactional
    public long create(ChatRequestDto dto, Principal principal) {
        log.trace("Started method create(ChatRequestDto)");
        log.debug("Provided parameter: {}", dto);

        Chat chat = dtoMapper.toChat(dto,
                LocalDate.now(),
                ChatStatus.HIDDEN,
                advertisementRepository.getReferenceById(dto.getAdvertisementId()),
                userRepository.getReferenceById(principalConverter.convert(principal)));
        return chatRepository.save(chat).getId();
    }

    @Override
    @Transactional
    public void remove(long chatId) {
        log.trace("Started method remove(long)");
        log.debug("Provided parameter chatId: {}", chatId);

        Chat chat = getChatById(chatId);
        chatRepository.delete(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<ChatResponseDto> findAllByPrincipal(Principal principal, Pageable pageable) {
        log.trace("Started findAllByUserId(long, Pageable) method");
        log.debug("Provided parameters principal: {}, pageable: {}", principal, pageable);

        return dtoMapper.toPageDto(chatRepository.findAllByUserId(principalConverter.convert(principal), pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public ChatResponseDto getByChatId(Principal principal, long chatId) {
        log.trace("Started findById(long) method");
        log.debug("Provided parameters principal : {}, chatId: {}", principal, chatId);

        return dtoMapper.toChatResponseDto(getChatById(chatId));

    }

    private Chat getChatById(long chatId) {
        log.trace("called private getChatById method(long)");
        log.debug("provided parameter chatId: {}", chatId);

        return chatRepository.findById(chatId)
                .orElseThrow(() -> {
                    NotFoundEntityException exception = new NotFoundEntityException();
                    log.error("Not found chat with provided chatId", exception);
                    return exception;
                });
    }

}
