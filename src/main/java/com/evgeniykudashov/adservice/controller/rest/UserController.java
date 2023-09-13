package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping(name = "/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDto> getUserDetails(Principal principal) {
        return ResponseEntity.ok(userService.findByPrincipal(principal));
    }
}
