package com.evgeniykudashov.adservice.service.suggestion;

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

import static com.evgeniykudashov.adservice.service.suggestion.AdvertisementTitleSuggestionProvider.ORDER;

@RequiredArgsConstructor
@Component
@Order(value = ORDER)
public class AdvertisementTitleSuggestionProvider implements AdvertisementSuggestionProvider {

    protected static final int ORDER = 2222;

    private final AdvertisementRepository advertisementRepository;

    public static Suggestion of(Advertisement advertisement, Pattern pattern) {
        Matcher matcher = pattern.matcher(advertisement.getTitle());
        matcher.find();

        return new Suggestion() {

            @Override
            public String getTitle() {
                return matcher.group(0);
            }

            @Override
            public String getUrl() {
                return MvcUriComponentsBuilder
                        .fromMethodName(AdvertisementsController.class, "findAdvertisements", matcher.group(), advertisement.getCategory().getId(), null)
                        .build().toUriString();
            }

            @Override
            public String getDescription() {
                return advertisement.getCategory().getTitle();
            }
        };
    }

    @Override
    public List<Suggestion> findSuggestions(String query, Pageable pageable) {

        List<Suggestion> suggestions = new ArrayList<>();

        Pattern pattern = Pattern.compile(String.format("\\b\\w*%s\\w*\\b", query), Pattern.CASE_INSENSITIVE);
        advertisementRepository.findAllByTitleContains(query, pageable)
                .forEach(advertisement -> suggestions.add(AdvertisementTitleSuggestionProvider.of(advertisement, pattern)));

        return suggestions;
    }
}
