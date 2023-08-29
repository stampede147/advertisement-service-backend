package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.ChatRequestDto;
import com.evgeniykudashov.adservice.dto.response.ChatResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.exception.servicelayer.ServiceLayerException;
import com.evgeniykudashov.adservice.mapper.ChatMapper;
import com.evgeniykudashov.adservice.model.chat.Chat;
import com.evgeniykudashov.adservice.model.chat.ChatStatus;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.ChatRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.ChatService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;


@AllArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    private AdvertisementRepository advertisementRepository;

    private UserRepository userRepository;

    private ChatMapper dtoMapper;

    @Override
    @Transactional
    public long create(ChatRequestDto dto) {
        log.trace("Started method create(ChatRequestDto)");
        log.debug("Provided parameter: {}", dto);

        Chat chat = dtoMapper.toChat(dto, LocalDate.now());
        chat.setStatus(ChatStatus.HIDDEN);

        User advertisementOwner = chat.getAdvertisement().getOwner();
        chat.getParticipants().add(advertisementOwner);

        return chatRepository.save(chat)
                .getId();
    }

    private void validateAdvertisementOwnerPresenceInChatParticipants(Chat chat) {
        User advertisementOwner = chat.getAdvertisement().getOwner();
        if (!chat.getParticipants().contains(advertisementOwner)) {
            ServiceLayerException serviceLayerException = new ServiceLayerException("Chat does not contain advertisement owner");
            log.error("unexpected exception", serviceLayerException);
            throw serviceLayerException;
        }
    }

    @Override
    @Transactional
    public void remove(long chatId) {
        log.trace("Started method remove(long)");
        log.debug("Provided parameter chatId: {}", chatId);

        Chat chat = findChatById(chatId);

        chatRepository.delete(chat);
    }

    @Override
    @Transactional(readOnly = true)
    public PageDto<ChatResponseDto> findAllByUserId(long userId, Pageable pageable) {
        log.trace("Started findAllByUserId(long, Pageable) method");
        log.debug("Provided parameters userId: {}, pageable: {}", userId, pageable);

        return dtoMapper.toPageDto(chatRepository.findByUsersIds(userId, pageable));
    }

    @Override
    @Transactional(readOnly = true)
    public ChatResponseDto findById(long chatId) {
        log.trace("Started findById(long) method");
        log.debug("Provided parameters chatId: {}", chatId);

        return dtoMapper.toChatResponseDto(findChatById(chatId));

    }

    private Chat findChatById(long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> {
                    NotFoundEntityException exception = new NotFoundEntityException();
                    log.error("Unexpected exception: {}", exception);
                    return exception;
                });
    }

}
