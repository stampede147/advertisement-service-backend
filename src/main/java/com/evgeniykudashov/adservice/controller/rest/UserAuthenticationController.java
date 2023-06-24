package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.mapper.dto.authentication.CreateAuthenticationRequestDto;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentications")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserAuthenticationController {

    public static final String ACCESS_TOKEN = "access_token";
    protected AuthenticationService authenticationService;

    private static Cookie getSecuredHttpOnlyCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }

    @SneakyThrows
    @PostMapping("/jwt")
    public ResponseEntity<Void> createJwtAuthentication(@RequestBody CreateAuthenticationRequestDto requestDto, HttpServletResponse httpResponse) {
        String token = authenticationService.generateJwtToken(requestDto.getUsername(), requestDto.getPassword());

        httpResponse.addCookie(getSecuredHttpOnlyCookie(ACCESS_TOKEN, token));

        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<?> get(@AuthenticationPrincipal Object obj) {

        return ResponseEntity.ok(obj.toString());
    }


}
