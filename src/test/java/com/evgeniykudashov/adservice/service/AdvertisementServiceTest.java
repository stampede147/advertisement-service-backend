package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.TestValues;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.statuses.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.domain.aggregate.advertisement.valueobject.Address;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.impl.AdvertisementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AdvertisementServiceTest {

    private static final long ID = 1L;

    @Mock()
    AdvertisementRepository advertisementRepository;

    @InjectMocks
    AdvertisementServiceImpl sut;


    @Test
    void create_advertisement() {
        Advertisement advertisement = TestValues.getAdvertisementObject();
        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class))).thenReturn(advertisement);

        sut.create(advertisement);

        Mockito.verify(advertisementRepository).save(Mockito.any(Advertisement.class));
    }

    @Test
    void update_advertisement_title() {
        Advertisement advertisement = TestValues.getAdvertisementObject();
        Mockito.when(advertisementRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(advertisement));
        Title title = new Title("titlev2");

        sut.updateTitle(title, ID);


        Assertions.assertEquals(title, advertisement.getTitle());
    }

    @Test
    void update_advertisement_address() {
        Address address = new Address(1234, "qwe", "qwe", "qwe");
        Advertisement advertisement = TestValues.getAdvertisementObject();
        Mockito.when(advertisementRepository.findById(ID)).thenReturn(Optional.of(advertisement));

        sut.updateAddress(address, ID);

        Assertions.assertEquals(address, advertisement.getAddress());
    }

    @Test
    void update_advertisement_status() {
        AdvertisementStatus anyStatus = AdvertisementStatus.DELETED;
        Advertisement advertisement = TestValues.getAdvertisementObject();
        Mockito.when(advertisementRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(advertisement));

        sut.updateStatus(anyStatus, ID);

        Assertions.assertEquals(anyStatus, advertisement.getStatus());
    }

    @Test
    void remove_advertisement_by_id() {
        //arrange

        //act
        sut.remove(ID);

        //assert
        Mockito.verify(advertisementRepository).deleteById(ID);
    }


    @Test
    void find_all_advertisements_by_user_id() {

        sut.findAllByUserId(ID);

        Mockito.verify(advertisementRepository).findAllByOwnerId(Mockito.any(Long.class));
    }

    @Test
    void find_all_advertisements_by_title() {
        Title title = TestValues.getTitleObject();

        sut.findAllByTitle(title);

        Mockito.verify(advertisementRepository).findAllByTitle(Mockito.any(Title.class));
    }

    @Test
    void find_all_advertisements_by_Category_id() {

        sut.findAllByCategoryId(ID);

        Mockito.verify(advertisementRepository).findAllByCategoryId(Mockito.any(Long.class));
    }
}