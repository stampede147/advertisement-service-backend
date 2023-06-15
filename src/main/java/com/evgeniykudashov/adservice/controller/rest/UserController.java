package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.model.domain.aggregate.user.User;
import com.evgeniykudashov.adservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private ObjectMapper jsonMapper;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity
                .created(ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/{id}")
                        .build(userService.create(user)))
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> patchUser(@RequestBody Map<String, Object> map, @PathVariable Long id) {

        if (!map.containsKey("id") || !id.equals(Long.valueOf(map.get("id").toString()))) {
            return ResponseEntity.badRequest().build();
        }
        map.remove("id");


        //converting all objects (that are linkedHashMap) from requestBody to concrete objects due to User.class
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Field field = ReflectionUtils.findField(User.class, entry.getKey());
            if (Objects.isNull(field)) {
                return ResponseEntity.badRequest().build();
            } else {
                map.put(entry.getKey(), jsonMapper.convertValue(entry.getValue(), field.getType()));
            }
        }

        userService.patchUpdate(map, id);

        return ResponseEntity.noContent().build();
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
