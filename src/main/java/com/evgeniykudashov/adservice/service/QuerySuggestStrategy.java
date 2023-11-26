package com.evgeniykudashov.adservice.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuerySuggestStrategy {

    List<Suggestion> doSuggest(String query, Pageable pageable);

    interface Suggestion {

        String getTitle();

        String getUrl();

        String getDescription();

    }
}
