package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.dto.response.UserResponseDto;
import com.evgeniykudashov.adservice.service.UserService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Tag(name = "User", description = "Provides API about user")
@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/v1/users",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private UserService userService;

    @Operation(description = "creates user",
            tags = "User",
            responses = {
                    @ApiResponse(responseCode = "201",
                            description = "user created successfully",
                            headers = @Header(name = HttpHeaders.LOCATION, description = "the location of created user"))
            })
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Validated(value = CreateConstraint.class) UserRequestDto dto) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/{id}")
                        .build(userService.create(dto)))
                .build();
    }

    @Operation(description = "deletes user by its ID",
            security = @SecurityRequirement(name = "jwt authentication"),
            tags = "User",
            parameters = @Parameter(name = "id", description = "The ID of user that should be deleted"),
            responses = @ApiResponse(responseCode = "200", description = "user deleted successfully"))
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        userService.remove(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(description = "returns details that corresponds user with such id",
            security = @SecurityRequirement(name = "jwt authentication"),
            tags = "User",
            parameters = @Parameter(name = "id", description = "the ID of user"),
            responses = @ApiResponse(responseCode = "200", description = "returns user details"))
    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(description = "returns details that corresponds user with such username",
            security = @SecurityRequirement(name = "jwt authentication"),
            tags = "User",
            parameters = @Parameter(name = "id", description = "the username of user"),
            responses = @ApiResponse(responseCode = "200", description = "returns user details"))
    @GetMapping(params = "username", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<UserResponseDto> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
