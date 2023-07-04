package com.evgeniykudashov.adservice.mapper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackResponseAdvertisementDto {

    private long advertisementId;

    private String title;
}
