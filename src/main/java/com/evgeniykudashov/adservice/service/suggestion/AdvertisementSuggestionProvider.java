package com.evgeniykudashov.adservice.service.suggestion;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdvertisementSuggestionProvider {

    default List<Suggestion> findSuggestions(String query, int size) {
        return findSuggestions(query, Pageable.ofSize(size));
    }

    List<Suggestion> findSuggestions(String query, Pageable pageable);
}
