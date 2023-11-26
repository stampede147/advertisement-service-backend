package com.evgeniykudashov.adservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
@RequiredArgsConstructor
public class QuerySuggestStrategyDelegate implements QuerySuggestStrategy {

    private final List<QuerySuggestStrategy> strategies;

    @Override
    public List<Suggestion> doSuggest(String query, Pageable pageable) {
        strategies.forEach(s -> System.out.println(s.getClass()));

        int pageSize = pageable.getPageSize();
        List<Suggestion> currentList = new ArrayList<>(pageSize);

        for (int i = 0; i < strategies.size() && currentList.size() < pageSize; i++) {
            currentList.addAll(getStrategyByIndex(query, Pageable.ofSize(pageSize - currentList.size()), i));
        }

        return currentList;
    }

    public List<Suggestion> getStrategyByIndex(String query, Pageable pageable, int i) {
        return strategies.get(i).doSuggest(query, pageable);
    }
}