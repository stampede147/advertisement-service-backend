package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.dto.UsernamePasswordDto;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentications")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationController {

    public static final String ACCESS_TOKEN = "access_token";
    protected AuthenticationService authenticationService;

    @PostMapping("/jwt")
    public ResponseEntity<Void> createJwtAuthentication(@RequestBody UsernamePasswordDto dto) {
        return ResponseEntity.noContent()
                .headers(headers -> headers.add(HttpHeaders.SET_COOKIE,
                        ResponseCookie.from(ACCESS_TOKEN, authenticationService.generateJwtToken(dto))
                                .httpOnly(true)
                                .secure(true)
                                .build()
                                .toString()))
                .build();
    }
}
