package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.UsernamePasswordRequestDto;
import com.evgeniykudashov.adservice.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "provides API about authentication")

@RestController
@RequestMapping(value = "/api/v1/authentications",

        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor(onConstructor_ = @Autowired)
public class JwtAuthenticationController {

    public static final String ACCESS_TOKEN = "access_token";

    protected AuthenticationService authenticationService;

    @Operation(description = "Provides authentication details")
    @ApiResponse(responseCode = "204",
            description = "(NO CONTENT) provides API access token in cookie",
            headers = @Header(
                    name = HttpHeaders.SET_COOKIE,
                    description = "provides jwt token for authentication, after " + ACCESS_TOKEN + "=",
                    schema = @Schema(
                            example = ACCESS_TOKEN + "=eyJhbGciO9.eyJzdWIY5MDE3NTQ1NH0.e2iEpdV0K8IZV48")
            ))
    @PostMapping("/jwt")
    public ResponseEntity<Void> createJwtAuthentication(@RequestBody @Valid UsernamePasswordRequestDto dto) {
        return ResponseEntity.noContent()
                .headers(headers -> headers.add(HttpHeaders.SET_COOKIE,
                        ResponseCookie.from(ACCESS_TOKEN, authenticationService.generateJwtToken(dto))
//                                .httpOnly(true)
//                                .secure(true)
                                .sameSite("strict")
                                .path("/")
                                .build()
                                .toString()))
                .build();
    }
}
