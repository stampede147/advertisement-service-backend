package com.evgeniykudashov.adservice.service.suggestion;

import java.util.List;

public interface SuggestionProviderManager {

    List<Suggestion> doSuggest(String query, int size);

}
