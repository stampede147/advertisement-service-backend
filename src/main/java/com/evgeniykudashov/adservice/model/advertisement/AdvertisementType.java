package com.evgeniykudashov.adservice.model.advertisement;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public enum AdvertisementType {

    ADVERTISEMENT(1, Constant.ADVERTISEMENT),
    CSA_ADVERTISEMENT(2, Constant.CSA_ADVERTISEMENT),
    CLOTHING_ADVERTISEMENT(3, Constant.CLOTHING_ADVERTISEMENT),
    SHOE_ADVERTISEMENT(4, Constant.SHOE_ADVERTISEMENT);

    final int id;

    @JsonValue
    final String title;

    public static class Utility {

        private static final List<AdvertisementType> cache = Arrays.asList(AdvertisementType.class.getEnumConstants());

        @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
        public static AdvertisementType valueForId(int id) {

            return cache.stream()
                    .filter(type -> type.id == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Not found AdvertisementType value with provided id: " + id));

        }

        public static AdvertisementType valueForTitle(String title) {

            return cache.stream()
                    .filter(type -> type.title.equals(title))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Not found AdvertisementType value with provided title: " + title));
        }

        public static int idForType(AdvertisementType type) {
            return type.getId();
        }

    }

    public static class Constant {

        public static final String ADVERTISEMENT = "ADVERTISEMENT";

        public static final String CSA_ADVERTISEMENT = "CSA_ADVERTISEMENT";

        public static final String CLOTHING_ADVERTISEMENT = "CSA_ADVERTISEMENT_CLOTHING";

        public static final String SHOE_ADVERTISEMENT = "SHOE_ADVERTISEMENT";
    }

}

