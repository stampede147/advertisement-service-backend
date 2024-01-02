package com.evgeniykudashov.adservice.dto.response;

import com.evgeniykudashov.adservice.service.suggestion.Suggestion;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AdvertisementSuggestResponseDto {

    @JsonInclude
    List<Suggestion> suggestions;

}
