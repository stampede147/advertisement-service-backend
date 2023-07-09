package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisement.ShoeSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoeSizeRepository extends JpaRepository<ShoeSize, Integer> {
}
