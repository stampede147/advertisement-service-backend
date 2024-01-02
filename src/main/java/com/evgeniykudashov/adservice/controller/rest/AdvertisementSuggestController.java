package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.AdvertisementSuggestRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementSuggestResponseDto;
import com.evgeniykudashov.adservice.service.AdvertisementSuggestQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "/suggest")
public class AdvertisementSuggestController {

    private final AdvertisementSuggestQueryService advertisementSuggestService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvertisementSuggestResponseDto suggestAdvertisement(@RequestBody AdvertisementSuggestRequestDto requestDto,
                                                                @RequestParam(defaultValue = "8") int size) {

        return advertisementSuggestService.suggestAdvertisements(requestDto, size);
    }
}
