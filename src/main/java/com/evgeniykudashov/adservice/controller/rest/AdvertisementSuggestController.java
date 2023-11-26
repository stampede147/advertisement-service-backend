package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementSuggestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "/suggest")
public class AdvertisementSuggestController {

    private final AdvertisementSuggestQueryService advertisementSuggestService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvertisementSuggestResponseDto suggestAdvertisement(@RequestBody AdvertisementSuggestRequestDto requestDto,
                                                                @PageableDefault(size = 8) Pageable pageable) {
        return advertisementSuggestService.suggestAdvertisements(requestDto, pageable);
    }
}
