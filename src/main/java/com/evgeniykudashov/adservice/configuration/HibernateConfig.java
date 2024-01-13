package com.evgeniykudashov.adservice.configuration;

import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
import jakarta.persistence.AttributeConverter;

public class HibernateConfig {

    public static class AdvertisementTypeAttributeConverter implements AttributeConverter<AdvertisementType, String> {
        @Override
        public String convertToDatabaseColumn(AdvertisementType attribute) {
            return attribute.getTitle();
        }

        @Override
        public AdvertisementType convertToEntityAttribute(String dbData) {
            return AdvertisementType.Utility.valueForTitle(dbData);
        }
    }

}
