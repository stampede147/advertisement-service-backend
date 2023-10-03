package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(path = "/api/v1/images")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ImageEntityResponseDto> saveImage(@RequestPart(name = "image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(imageService.saveImage(multipartFile));
    }

    @GetMapping(path = "/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<Resource> getImage(@PathVariable String id) throws IOException {

        Resource imageResource = imageService.findById(id);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(1800, TimeUnit.SECONDS).mustRevalidate())
                .contentType(MediaType.valueOf(URLConnection.guessContentTypeFromName(imageResource.getFilename())))
                .body(imageResource);
    }
}
