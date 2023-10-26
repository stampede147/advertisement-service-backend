package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Tag(name = "Authentication", description = "provides API about authentication")

@RestController
@RequestMapping(value = "/api/v1/authentications/jwt",

        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class JwtAuthenticationController {

    public static final String REFRESH_TOKEN_COOKIE = "refresh";

    private final long refreshTokenLifeMillis;

    protected AuthenticationService authenticationService;

    @Autowired
    public JwtAuthenticationController(@Value("${application.security.jwt.refreshLifeMillis}") long refreshTokenLifeMillis,
                                       AuthenticationService authenticationService) {
        this.refreshTokenLifeMillis = refreshTokenLifeMillis;
        this.authenticationService = authenticationService;
    }

    private static String getRefreshTokenCookieSettings(String refreshToken, long refreshTokenLifeMillis) {
        return ResponseCookie.from(REFRESH_TOKEN_COOKIE, refreshToken)
                .httpOnly(true)
                .sameSite("strict")
                .maxAge(Duration.ofMillis(refreshTokenLifeMillis))
                .path("/api/v1/authentications/jwt")
                .build()
                .toString();
    }

    @Operation(description = "Provides authentication details")
    @ApiResponse(responseCode = "204",
            description = "(NO CONTENT) provides API refresh token in cookie and access token in response body",
            headers = @Header(
                    name = HttpHeaders.SET_COOKIE,
                    description = "provides refresh token for re-authentication, after " + REFRESH_TOKEN_COOKIE + "=",
                    schema = @Schema(example = REFRESH_TOKEN_COOKIE + "=eyJhbGciO9.eyJzdWIY5MDE3NTQ1NH0.e2iEpdV0K8IZV48")
            ))
    @PostMapping("")
    public ResponseEntity<String> refreshJwtAuthentication(@RequestBody @Valid UsernamePasswordRequestDto dto) {

        String accessToken = authenticationService.generateJwtToken(dto);

        String refreshToken = authenticationService.generateRefreshToken(accessToken);

        return ResponseEntity.ok()
                .headers(headers -> headers.add(HttpHeaders.SET_COOKIE, getRefreshTokenCookieSettings(refreshToken, this.refreshTokenLifeMillis)))
                .body(accessToken);
    }

    @Operation(description = "Provides authentication details by refresh procedure")
    @ApiResponse(responseCode = "204",
            description = "(NO CONTENT) provides API refresh token in cookie and access token in response body",
            headers = @Header(
                    name = HttpHeaders.SET_COOKIE,
                    description = "provides refresh token for re-authentication, after " + REFRESH_TOKEN_COOKIE + "=",
                    schema = @Schema(example = REFRESH_TOKEN_COOKIE + "=eyJhbGciO9.eyJzdWIY5MDE3NTQ1NH0.e2iEpdV0K8IZV48")
            ))
    @PostMapping(path = "/refresh")
    public ResponseEntity<?> refreshJwtAuthentication(@CookieValue(name = REFRESH_TOKEN_COOKIE)
                                                      @NotEmpty String refreshTokenRaw) {

        String accessToken = authenticationService.generateAccessToken(refreshTokenRaw, LocalDateTime.now());

        return ResponseEntity.ok()
                .headers(headers -> headers.add(HttpHeaders.SET_COOKIE, getRefreshTokenCookieSettings(this.authenticationService.generateRefreshToken(accessToken), this.refreshTokenLifeMillis)))
                .body(accessToken);
    }
}
