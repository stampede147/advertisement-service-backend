package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.image.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageEntityRepository extends JpaRepository<ImageEntity, String> {

    Optional<ImageEntity> findByLocation(String location);
}
