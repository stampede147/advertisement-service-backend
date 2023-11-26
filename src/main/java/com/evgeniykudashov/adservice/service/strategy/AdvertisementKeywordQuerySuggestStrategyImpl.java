package com.evgeniykudashov.adservice.service.strategy;

import com.evgeniykudashov.adservice.controller.rest.AdvertisementsController;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.evgeniykudashov.adservice.service.strategy.AdvertisementKeywordQuerySuggestStrategyImpl.ORDER;

@RequiredArgsConstructor
@Component
@Order(value = ORDER)
public class AdvertisementKeywordQuerySuggestStrategyImpl implements QuerySuggestStrategy {

    protected static final int ORDER = 2222;

    private final AdvertisementRepository advertisementRepository;

    private static Suggestion createSuggestion(Advertisement advertisement, String title) {
        return new Suggestion() {
            @Override
            public String getTitle() {
                return title;
            }

            @Override
            public String getUrl() {
                return MvcUriComponentsBuilder.fromMethodName(AdvertisementsController.class,
                                "findAdvertisements",
                                title,
                                advertisement.getCategory().getId())
                        .build().toString();
            }

            @Override
            public String getDescription() {
                return advertisement.getCategory().getTitle();
            }
        };
    }

    @Override
    public List<Suggestion> doSuggest(String query, Pageable pageable) {
        return mapToSuggestion(advertisementRepository.findAllByTitleContains(query, pageable),
                Pattern.compile(String.format("\\b\\w*%s\\w*\\b", query), Pattern.CASE_INSENSITIVE));
    }

    private List<Suggestion> mapToSuggestion(List<Advertisement> advertisements, Pattern pattern) {

        ArrayList<Suggestion> suggestions = new ArrayList<>(advertisements.size());
        for (int i = 0; i < advertisements.size(); i++) {

            Advertisement advertisement = advertisements.get(i);
            Matcher matcher = pattern.matcher(advertisement.getTitle());

            if (matcher.find()) {
                suggestions.add(i, createSuggestion(advertisement, matcher.group()));
            }
        }

        return suggestions;

    }
}
