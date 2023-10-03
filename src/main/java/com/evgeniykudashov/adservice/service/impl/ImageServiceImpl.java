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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class ImageServiceImpl implements ImageService {

    private final static String PATH_IMAGE_LOCATION = "static/images/";
    private final ImageEntityRepository imageEntityRepository;
    private final ImageEntityMapper imageEntityMapper;
    private final List<String> supportedContentTypes = new ArrayList<>();
    private final Path root = Paths.get(PATH_IMAGE_LOCATION);

    @PostConstruct
    public void init() throws IOException {
        supportedContentTypes.add(MediaType.IMAGE_JPEG_VALUE);
        supportedContentTypes.add(MediaType.IMAGE_PNG_VALUE);

        Files.createDirectories(Path.of(PATH_IMAGE_LOCATION));

    }

    /**
     * @param file that has to be saved
     * @throws IOException
     */
    public Path saveImageInPath(String filename, Path path, MultipartFile file) throws IOException {

        if (file.getContentType() == null) {
            throw new RuntimeException("file content type is null");
        }

        Path filePath = path.resolve(filename
                .concat(".")
                .concat(file.getContentType() // extension
                        .substring(file.getContentType().lastIndexOf("/") + 1)));

        Files.copy(file.getInputStream(), filePath);

        return filePath;
    }


    @Override
    @Transactional
    public ImageEntityResponseDto saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new RuntimeException("provided file is empty");
        }

        if (supportedContentTypes.stream().noneMatch(type -> type.equals(file.getContentType()))) {
            throw new RuntimeException("provided file is not supported");
        }

        String imageName = UUID.randomUUID().toString();

        Path path = saveImageInPath(imageName, root, file);

        ImageEntity imageEntity = imageEntityMapper.toImageEntity(imageName, path.toUri().toString());

        ImageEntity save = imageEntityRepository.save(imageEntity);

        return imageEntityMapper.toResponseDto(save);
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
