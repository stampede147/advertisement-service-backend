package com.evgeniykudashov.adservice.repository.advertisementrepository;

import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import org.springframework.data.repository.ListCrudRepository;

public interface AdvertisementRepository extends ListCrudRepository<Advertisement, Long> {
}
