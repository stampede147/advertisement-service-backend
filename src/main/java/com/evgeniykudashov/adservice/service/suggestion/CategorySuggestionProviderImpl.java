package com.evgeniykudashov.adservice.service.suggestion;

import com.evgeniykudashov.adservice.controller.rest.AdvertisementsController;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.evgeniykudashov.adservice.service.suggestion.CategorySuggestionProviderImpl.ORDER;

@Component
@Order(value = ORDER)
@RequiredArgsConstructor
public class CategorySuggestionProviderImpl implements AdvertisementSuggestionProvider {

    protected static final int ORDER = 2220;

    private final CategoryRepository categoryRepository;

    public static Suggestion of(Category category, Pattern pattern) {
        Matcher matcher = pattern.matcher(category.getTitle());
        matcher.find();

        return new Suggestion() {

            @Override
            public String getTitle() {
                return matcher.group();
            }

            @Override
            public String getUrl() {
                return MvcUriComponentsBuilder
                        .fromMethodName(AdvertisementsController.class, "findAdvertisements", matcher.group(), category.getId(), null)
                        .build().toUriString();
            }

            @Override
            public String getDescription() {
                return category.getTitle();
            }
        };
    }

    @Override
    public List<Suggestion> findSuggestions(String query, Pageable pageable) {

        List<Suggestion> suggestions = new ArrayList<>();

        Pattern pattern = Pattern.compile(String.format("\\b\\w*%s\\w*\\b", query), Pattern.CASE_INSENSITIVE);
        categoryRepository.findAllByTitle(query)
                .forEach(category -> suggestions.add(CategorySuggestionProviderImpl.of(category, pattern)));

        return suggestions;
    }
}
