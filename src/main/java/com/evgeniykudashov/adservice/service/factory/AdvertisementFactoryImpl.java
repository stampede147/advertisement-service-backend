package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

@Component
public class AdvertisementFactoryImpl implements AdvertisementFactory {

    @Override
    public Advertisement createAdvertisement(Supplier<Advertisement> advertisementSupplier,
                                             Supplier<User> sellerSupplier,
                                             Supplier<Category> categorySupplier,
                                             Supplier<List<ImageEntity>> imagesSupplier) {

        Advertisement advertisement = advertisementSupplier.get();


        advertisement.setStatus(AdvertisementStatus.CREATED);

        advertisement.setStartTime(LocalDate.now());

        advertisement.setSeller(sellerSupplier.get());

        advertisement.setCategory(categorySupplier.get());

        advertisement.setImages(imagesSupplier.get());


        return advertisement;
    }
}