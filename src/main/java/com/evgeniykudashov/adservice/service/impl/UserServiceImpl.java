package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.ImageEntityMapper;
import com.evgeniykudashov.adservice.mapper.UserMapper;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.ImageEntityRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.service.UserService;
import com.evgeniykudashov.adservice.service.utils.UserFactory;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.security.Principal;
import java.util.function.Supplier;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ImageEntityRepository imageEntityRepository;

    private final UserFactory userFactory;

    private final UserMapper mapper;

    private final PasswordEncoder encoder;

    private final ImageEntityMapper imageEntityMapper;

    private final Converter<Principal, Long> principalConverter;

    private static String getDirectoryPath(String name) {
        return name.substring(0, 1).toUpperCase();
    }

    @Transactional
    @Override
    @SneakyThrows
    public long create(UserRequestDto dto) {
        log.trace("Started create(UserRequestDto) method");
        log.debug("Provided parameter dto: {}", dto);

        String stubAvatarPathLocation = getPathLocation(dto.firstName);

        Supplier<ImageEntity> getImageEntityCallback = () -> imageEntityRepository.findByLocation(stubAvatarPathLocation)
                .orElseGet(() -> imageEntityRepository.save(new ImageEntity(null, stubAvatarPathLocation)));

        User user = userFactory.createUser(dto, getImageEntityCallback);


        return userRepository.save(user).getId();
    }

    private String getPathLocation(String name) {

        final String path = "static/images/stub_avatars";

        return Path.of(path)
                .resolve(getDirectoryPath(name))
                .resolve(String.format("%s.png", "256x256"))
                .toUri()
                .toString();
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
        return userRepository.findById(userId).orElseThrow(NotFoundEntityException::new);
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
