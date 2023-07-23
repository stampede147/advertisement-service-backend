package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.exception.servicelayer.PasswordMismatchException;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.security.jwt.tokenfactory.OAuthTokenFactory;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OAuthTokenFactory factory;

    @Transactional(readOnly = true)
    public String generateJwtToken(UsernamePasswordRequestDto dto) {
        log.trace("Started generateJwtToken(UsernamePasswordRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        User user = findUserByUsername(dto.getUsername());

        validatePassword(dto.getPassword(), user.getPassword());

        return factory.createToken(user.getUsername(), Collections.singleton(user.getRole()));
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
            PasswordMismatchException exception = new PasswordMismatchException("provided password is wrong");
            log.error("Unexpected exception: {}", exception);
            throw exception;
        }
    }

}
