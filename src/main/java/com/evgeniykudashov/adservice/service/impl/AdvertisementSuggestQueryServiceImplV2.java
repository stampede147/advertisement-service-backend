package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementSuggestQueryService;
import com.evgeniykudashov.adservice.service.suggestion.Suggestion;
import com.evgeniykudashov.adservice.service.suggestion.SuggestionProviderManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Primary
@RequiredArgsConstructor
public class AdvertisementSuggestQueryServiceImplV2 implements AdvertisementSuggestQueryService {

    private final SuggestionProviderManager providerManager;

    @Override
    @Transactional(readOnly = true)
    public AdvertisementSuggestResponseDto suggestAdvertisements(AdvertisementSuggestRequestDto requestDto, int size) {

        final String query = requestDto.query;

        List<Suggestion> advertisements = providerManager.doSuggest(query, size);

        return new AdvertisementSuggestResponseDto(advertisements);
    }
}
