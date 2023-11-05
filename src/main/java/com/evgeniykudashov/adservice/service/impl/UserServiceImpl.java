package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.UserMapper;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper mapper;

    private final Converter<Principal, Long> principalConverter;

    @Transactional
    @Override
    @SneakyThrows
    public long create(UserRequestDto dto) {
        log.trace("Started create(Us erRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        User user = mapper.toUser(dto, null);

        return userRepository.save(user)
                .getId();
    }


    @Transactional
    @Override
    @PreAuthorize("hasRole(T(com.evgeniykudashov.adservice.model.user.Role).ADMIN)")
    public void remove(long userId) {
        log.trace("Started remove(long) method");
        log.debug("Provided parameter userId: {}", userId);

        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(long userId) {
        log.trace("Started findById(long) method");
        log.debug("Provided parameter userId: {}", userId);

        return mapper.toUserResponseDto(getUser(userId));
    }

    private User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(NotFoundEntityException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findByUsername(String username) {
        log.trace("Started findByUsername(String) method");
        log.debug("Provided parameter username: {}", username);

        return mapper.toUserResponseDto(getUser(username));


    }

    private User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(NotFoundEntityException::new);
    }

    @Override
    public UserResponseDto findByPrincipal(Principal principal) {
        return this.findById(principalConverter.convert(principal));
    }
}
