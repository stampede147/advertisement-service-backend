package com.evgeniykudashov.adservice.service.factory;

import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.image.ImageEntity;
import com.evgeniykudashov.adservice.model.user.User;

import java.util.List;
import java.util.function.Supplier;

public interface AdvertisementFactory {


    Advertisement createAdvertisement(Supplier<Advertisement> advertisementSupplier,
                                      Supplier<User> sellerSupplier,
                                      Supplier<Category> categorySupplier,
                                      Supplier<List<ImageEntity>> imagesSupplier);

    default Advertisement createAdvertisement() {
        return createAdvertisement(Advertisement::new, () -> null, () -> null, () -> null);
    }

}
