package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.service.NotFoundEntityException;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.model.domain.aggregate.user.valueobject.AccessDetails;
import com.evgeniykudashov.adservice.model.domain.shared.security.Username;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.security.jwt.tokenfactory.JwtTokenFactory;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;


@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    private ConfigurableApplicationContext applicationContext;

    private JwtTokenFactory factory;

    @Transactional(readOnly = true)
    @SneakyThrows
    public String generateJwtToken(AccessDetails accessDetails) {
        User user = findUserByUsername(accessDetails.getUsername());
        accessDetails = user.getAccessDetails();
        new ObjectMapper().writeValue(new File("java.json"), accessDetails);
        return factory.createToken(accessDetails.getUsername().getUsername(), accessDetails.getAuthorities());
    }

    private User findUserByUsername(Username username) {
        return userRepository.findByUsername(username).orElseThrow(NotFoundEntityException::new);
    }

}
