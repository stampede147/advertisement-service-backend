package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.response.ImageEntityResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.ImageEntityMapper;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.repository.ImageEntityRepository;
import com.evgeniykudashov.adservice.service.ImageService;
import jakarta.annotation.PostConstruct;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageEntityRepository imageEntityRepository;

    private final ImageEntityMapper imageEntityMapper;

    private final List<String> supportedContentTypes = List.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);


    private static Path resolveFilePath(Path path, String id, String contentType) {

        String filename = resolveFilenameForPath(id, contentType);

        return path.resolve(filename);
    }

    private static String resolveFilenameForPath(String id, String contentType) {
        return String.format("%s.%s", id, contentType.substring(contentType.lastIndexOf("/") + 1));
    }

    @PostConstruct
    public void init() throws IOException {
        createDirectoryIfNotExists(getPath());
    }

    private void createDirectoryIfNotExists(Path directory) {
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create directory: " + directory, e);
            }
        }
    }


    @Override
    @Transactional
    public ImageEntityResponseDto saveImage(MultipartFile image) throws IOException {

        validateFile(image);

        String id = UUID.randomUUID().toString();

        Path path = saveImage(image, id);

        ImageEntity savedEntity = saveEntity(id, path);

        return imageEntityMapper.toResponseDto(savedEntity);
    }

    private Path saveImage(MultipartFile file, String id) throws IOException {
        Path path = getPath();

        //creating directory if not exists
        File directory = path.toFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path filePath = resolveFilePath(path, id, file.getContentType());

        Files.copy(file.getInputStream(), filePath);

        return filePath;
    }

    private ImageEntity saveEntity(String id, Path path) {
        ImageEntity entity = imageEntityMapper.toImageEntity(id, path.toUri().toString());

        ImageEntity saved = imageEntityRepository.save(entity);

        return saved;
    }

    private Path getPath() {
        return Path.of("static/images/")
                .resolve(LocalDate.now().toString());
    }

    private void validateFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("provided file is empty");
        }

        if (!supportedContentTypes.contains(file.getContentType())) {
            throw new RuntimeException("provided file is not supported");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource findById(@NonNull String id) {
        return imageEntityRepository.findById(id)
                .map(ImageEntity::getLocation)
                .map(UrlResource::from)
                .orElseThrow(NotFoundEntityException::new);
    }
}
