package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.advertisement.ThingColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThingColorRepository extends JpaRepository<ThingColor, String> {
}
