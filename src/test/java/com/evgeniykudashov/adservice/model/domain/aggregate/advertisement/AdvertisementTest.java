package com.evgeniykudashov.adservice.model.domain.aggregate.advertisement;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Description;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AdvertisementTest {

    @InjectMocks
    Advertisement sut;

    @Test
    void can_update_title() {
        Title title = TestValues.getTitleObject();

        sut.updateTitle(title);

        Assertions.assertEquals(title, sut.getTitle());
    }

    @Test
    void can_update_address() {
        Address address = TestValues.getAddressObject();

        sut.updateAddress(address);

        Assertions.assertEquals(address, sut.getAddress());
    }

    @Test
    void can_update_status() {
        AdvertisementStatus status = AdvertisementStatus.ARCHIVED;

        sut.updateStatus(status);

        Assertions.assertEquals(status, sut.getStatus());
    }

    @Test
    void can_update_description() {
        Description description = TestValues.getDescriptionObject();

        sut.updateDescription(description);

        Assertions.assertEquals(description, sut.getDescription());
    }

}