package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisement.ClothingSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingSizeRepository extends JpaRepository<ClothingSize, Integer> {
}
