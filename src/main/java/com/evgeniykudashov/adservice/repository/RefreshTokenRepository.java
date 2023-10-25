package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
}
