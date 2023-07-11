package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.dto.request.UserRequestDto;
import com.evgeniykudashov.adservice.service.UserService;
import com.evgeniykudashov.adservice.validation.CreateConstraint;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Validated(value = CreateConstraint.class) UserRequestDto dto) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/{id}")
                        .build(userService.create(dto)))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id) {
        userService.remove(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<?> getUserById(@PathVariable long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
}
