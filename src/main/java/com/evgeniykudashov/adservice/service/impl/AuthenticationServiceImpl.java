package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.exception.servicelayer.*;
import com.evgeniykudashov.adservice.model.RefreshToken;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.RefreshTokenRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.security.jwt.tokenfactory.OAuthTokenFactory;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import com.evgeniykudashov.adservice.service.factory.RefreshTokenFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final OAuthTokenFactory accessTokenFactory;

    private final RefreshTokenFactory refreshTokenFactory;

    private final RefreshTokenRepository refreshTokenRepository;

    private final Converter<Principal, Long> principalConverter;

    @Transactional(readOnly = true)
    public String generateJwtToken(UsernamePasswordRequestDto dto) {
        log.trace("Started generateJwtToken(UsernamePasswordRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        User user = findUserByUsername(dto.getUsername());

        validatePassword(dto.getPassword(), user.getPassword());

        return generateAccessTokenInternal(user);
    }

    @Override
    @Transactional()
    public String generateRefreshToken(String accessTokenRaw) {
        log.trace("Started generateRefreshToken(String");
        log.debug("Provided parameter accessTokenRaw: {}", accessTokenRaw);

        return refreshTokenRepository.save(generateRefreshTokenInternal(principalConverter.convert(accessTokenFactory.validateAndDecodeToken(accessTokenRaw))))
                .getId().toString();
    }

    private RefreshToken generateRefreshTokenInternal(Long userId) {
        return this.refreshTokenFactory.create(() -> userRepository.getReferenceById(userId));
    }

    @Override
    @Transactional
    public String generateAccessToken(@NonNull String refreshTokenId, @NonNull LocalDateTime callTime) throws IllegalArgumentException {
        log.trace("Started generateAccessToken(String, LocalDateTime");
        log.debug("Provided parameter refreshTokenId: {}, callTime: {}", refreshTokenId, callTime);

        try {
            return generateAccessToken(UUID.fromString(refreshTokenId), callTime);
        } catch (IllegalArgumentException e) {
            throw new IllegalRefreshTokenException(e);
        }

    }

    public String generateAccessToken(UUID tokenId, LocalDateTime currentTime) throws IllegalArgumentException {

        RefreshToken refreshToken = refreshTokenRepository.findById(tokenId)
                .orElseThrow(NotFoundRefreshTokenException::new);

        if (currentTime.isAfter(refreshToken.getExpiresAt())) {
            throw new ExpiredRefreshTokenException();
        }

        refreshTokenRepository.delete(refreshToken);

        return generateAccessTokenInternal(refreshToken.getUser());

    }

    private String generateAccessTokenInternal(User user) {
        return accessTokenFactory.createToken(Long.toString(user.getId()), Collections.singleton(user.getRole()));
    }

    private User findUserByUsername(String username) {
        log.trace("Started findUserByUsername(String");
        log.debug("Provided parameter username: {}", username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    NotFoundEntityException exception = new NotFoundEntityException();
                    log.error("Unexpected exception. Not found entity with provided username: {}", username, exception);
                    return exception;
                });
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        log.trace("Started validatePassword(String, String method");

        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            PasswordMismatchException exception = new PasswordMismatchException();
            log.error("Problems with validating password", exception);
            throw exception;
        }
    }

}
