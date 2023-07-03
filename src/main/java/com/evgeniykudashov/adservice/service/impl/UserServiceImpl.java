package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.UserMapper;
import com.evgeniykudashov.adservice.mapper.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.mapper.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.model.user.Role;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.UserService;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    private final Validator validator;

    @Transactional
    @Override
    public long create(UserRequestDto dto) {
        return userRepository.save(User.builder().id(0)
                .firstName(dto.firstName)
                .lastName(dto.lastName)
                .birthdate(dto.getBirthdate())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build()
        ).getId();
    }

    @Transactional
    @Override
    public void remove(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto findById(long userId) {
        return mapper.toUserResponseDto(userRepository.findById(userId).orElseThrow(NotFoundEntityException::new));
    }

}
