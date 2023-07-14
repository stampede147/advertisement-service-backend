package com.evgeniykudashov.adservice.model.advertisement;

import lombok.Getter;

public enum AdvertisementType {

    SHOE, CLOTHING;

    @Getter
    public static class Constants {
        public static final String SHOE = "SHOE";
        public static final String CLOTHING = "CLOTHING";
    }
}
