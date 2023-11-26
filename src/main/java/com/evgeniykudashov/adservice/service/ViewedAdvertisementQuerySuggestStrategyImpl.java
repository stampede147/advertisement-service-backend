package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.controller.rest.AdvertisementsController;
import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import com.evgeniykudashov.adservice.repository.ViewedAdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.evgeniykudashov.adservice.service.ViewedAdvertisementQuerySuggestStrategyImpl.ORDER;

@RequiredArgsConstructor
@Component
@Order(value = ORDER)
public final class ViewedAdvertisementQuerySuggestStrategyImpl implements QuerySuggestStrategy {

    static final int ORDER = 1111;

    private final ViewedAdvertisementRepository viewedAdvertisementRepository;
    private final Converter<Principal, Long> principalConverter;

    @Override
    public List<Suggestion> doSuggest(String query, Pageable pageable) {

        Long authenticatedUserId = principalConverter.convert(SecurityContextHolder.getContext().getAuthentication());
        if (authenticatedUserId == null) {
            return Collections.emptyList();
        }

        List<ViewedAdvertisement> viewedAdvertisements = viewedAdvertisementRepository.findViewedAdvertisementsByTitleContains(authenticatedUserId, query, pageable);
        return mapToList(viewedAdvertisements);
    }


    private List<Suggestion> mapToList(List<ViewedAdvertisement> viewedAdvertisements) {

        List<Suggestion> suggestions = new ArrayList<>(viewedAdvertisements.size());
        for (int i = 0; i < viewedAdvertisements.size(); i++) {
            suggestions.add(i, mapToSuggestion(viewedAdvertisements.get(i)));
        }

        return suggestions;

    }

    private Suggestion mapToSuggestion(ViewedAdvertisement viewedAdvertisement) {
        return new Suggestion() {
            @Override
            public String getTitle() {
                return viewedAdvertisement.getAdvertisement().getTitle();
            }

            @Override
            public String getUrl() {
                return MvcUriComponentsBuilder.fromMethodName(AdvertisementsController.class,
                                "findAdvertisements",
                                viewedAdvertisement.getAdvertisement().getTitle(),
                                viewedAdvertisement.getAdvertisement().getCategory().getId())
                        .build().getPath();
            }

            @Override
            public String getDescription() {
                return null;
            }

        };
    }
}
