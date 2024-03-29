package com.evgeniykudashov.adservice.service.suggestion;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdvertisementSuggestionProviderManagerImpl implements SuggestionProviderManager {

    private final List<AdvertisementSuggestionProvider> providers;

    @Override
    public List<Suggestion> doSuggest(String query, int searchElementSize) {
        return doSuggestInternal(query, searchElementSize, 0, new ArrayList<>(searchElementSize));
    }

    private List<Suggestion> doSuggestInternal(final String query,
                                               final int remainingElementSize,
                                               int index,
                                               final List<Suggestion> data) {
        if (remainingElementSize == 0 || index == providers.size()) {
            return data;
        }

        final int initSize = data.size();

        data.addAll(providers.get(index).findSuggestions(query, remainingElementSize));

        return doSuggestInternal(query, remainingElementSize - (data.size() - initSize), index + 1, data);
    }
}
