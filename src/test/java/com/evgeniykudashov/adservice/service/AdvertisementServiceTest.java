package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperContext;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.impl.AdvertisementServiceImpl;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@ExtendWith(MockitoExtension.class)
@Setter(onMethod_ = @Autowired)
class AdvertisementServiceTest {


    public static final AdvertisementRequestDto INPUT_DTO = new AdvertisementRequestDto();

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock(stubOnly = true)
    private AdvertisementMapperContext mapperContext;

    @InjectMocks
    private AdvertisementServiceImpl sut;

    @BeforeEach
    protected void beforeSetup() {
        AdvertisementMapper mock = Mockito.mock(AdvertisementMapper.class, Mockito.withSettings().stubOnly());

        Mockito.when(mapperContext.getMapper(Mockito.any()))
                .thenReturn(mock);
    }

    @Test
    void can_create_advertisement() {
        Advertisement notSavedAdvertisement = TestObjects.getNotSavedAdvertisement();
        Mockito.when(advertisementRepository.save(notSavedAdvertisement))
                .thenReturn(TestObjects.getSavedAdvertisement());
        AdvertisementRequestDto dto = TestObjects.getNotSavedAdvertisementRequestDto();
        Mockito.when(mapperContext.getMapper(Mockito.any())
                        .toAdvertisement(dto))
                .thenReturn(notSavedAdvertisement);


        long createdAdvertisementId = sut.createAdvertisement(dto);

        Assertions.assertEquals(createdAdvertisementId, TestObjects.SAVED_ENTITY_ID);
    }

    @Test
    void can_remove_exist_advertisement_in_repository() {
        final long ID = TestObjects.SAVED_ENTITY_ID;

        sut.removeAdvertisementById(ID);

        Mockito.verify(advertisementRepository).deleteById(ID);
    }

    @Test
    void should_return_page_with_advertisements_when_provided_user_id() {

        long userId = TestObjects.SAVED_ENTITY_ID;
        Pageable pageable = Mockito.mock(Pageable.class);

        Page<Advertisement> returnPage = Mockito.mock(Page.class);
        Mockito.when(advertisementRepository.findAllByOwnerId(userId, pageable)).thenReturn(returnPage);


        PageDto value = new PageDto<AdvertisementResponseDto>();
        value.setContent(List.of(new AdvertisementResponseDto()));
        Mockito.when(mapperContext.getMapper(Mockito.any()).toPageDto(Mockito.any()))
                .thenReturn(value);


        //act
        PageDto<AdvertisementResponseDto> result = sut.getPageByUserId(userId, pageable);


        Assertions.assertEquals(value, result);
    }

    @Test
    void should_return_advertisement_when_exists_by_id() {
        final long ID = TestObjects.SAVED_ENTITY_ID;

        AdvertisementResponseDto oneByAdvertisementId = sut.getOneByAdvertisementId(ID);

        Mockito.verify(advertisementRepository).findById(ID);
    }
}