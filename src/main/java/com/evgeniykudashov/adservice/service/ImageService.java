package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import lombok.NonNull;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    ImageEntityResponseDto saveImage(MultipartFile file) throws IOException;

    Resource findById(@NonNull String id);
}
