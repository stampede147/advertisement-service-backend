package com.evgeniykudashov.adservice.controller.rest;

import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.nio.file.Path;

@RestController
@RequestMapping(path = "/static")
public class StaticFilePathController {

    private final Path path = Path.of("static/images/stub_avatars");

    @GetMapping(path = "/stub_avatars/{dir}/{file}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> getUserAvatarStub(@PathVariable String dir, @PathVariable String file) {
        URI uri = Path.of("static/images/stub_avatars")
                .resolve(dir)
                .resolve(file)
                .toUri();
        return ResponseEntity.ok(UrlResource.from(uri));
    }
}
