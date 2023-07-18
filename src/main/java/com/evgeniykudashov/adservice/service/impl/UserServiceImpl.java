package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.InvalidIdException;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.UserMapper;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Transactional
    @Override
    public long create(UserRequestDto dto) {
        log.trace("Started create(UserRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        return userRepository.save(mapper.toUser(dto, encoder.encode(dto.getPassword()), Role.USER))
                .getId();
    }

    @Transactional
    @Override
    public void remove(long userId) {
        log.trace("Started remove(long) method");
        log.debug("Provided parameter userId: {}", userId);

        validateId(userId);

        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(long userId) {
        log.trace("Started findById(long) method");
        log.debug("Provided parameter userId: {}", userId);

        validateId(userId);

        return mapper.toUserResponseDto(userRepository.findById(userId).orElseThrow(NotFoundEntityException::new));
    }

    private void validateId(long userId) {
        log.trace("Started validateId(long) method");
        log.debug("Provided parameter userId: {}", userId);

        if (userId <= 0) {
            InvalidIdException exception = new InvalidIdException("provided id should be positive, id: " + userId);
            log.error("Unexpected exception", exception);
            throw exception;
        }
    }

}
