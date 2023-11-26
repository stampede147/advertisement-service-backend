package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementSuggestQueryService;
import com.evgeniykudashov.adservice.service.strategy.QuerySuggestStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdvertisementSuggestQueryServiceImpl implements AdvertisementSuggestQueryService {

    private final QuerySuggestStrategy querySuggestStrategy;

    @Autowired
    public AdvertisementSuggestQueryServiceImpl(@Qualifier("querySuggestStrategyDelegate") QuerySuggestStrategy querySuggestStrategy) {
        this.querySuggestStrategy = querySuggestStrategy;
    }

    @Override
    @Transactional(readOnly = true)
    public AdvertisementSuggestResponseDto suggestAdvertisements(AdvertisementSuggestRequestDto requestDto, Pageable pageable) {

        String query = requestDto.query;

        List<QuerySuggestStrategy.Suggestion> suggestions = querySuggestStrategy.doSuggest(query, pageable);

        return new AdvertisementSuggestResponseDto(suggestions);
    }
}
