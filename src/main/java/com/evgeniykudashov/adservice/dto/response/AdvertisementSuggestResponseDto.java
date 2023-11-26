package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.service.strategy.QuerySuggestStrategy;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AdvertisementSuggestResponseDto {

    @JsonInclude
    List<QuerySuggestStrategy.Suggestion> results;


}
