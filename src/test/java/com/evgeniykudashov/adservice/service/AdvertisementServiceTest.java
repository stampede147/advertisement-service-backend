package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapperContext;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementType;
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

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@Setter(onMethod_ = @Autowired)
class AdvertisementServiceTest {


    public static final AdvertisementRequestDto INPUT_DTO = new AdvertisementRequestDto();

    @Mock(stubOnly = true)
    AdvertisementRepository advertisementRepository;

    @Mock(stubOnly = true)
    AdvertisementMapperContext mapperContext;

    @Mock(stubOnly = true)
    AdvertisementMapper advertisementMapper;

    @InjectMocks
    AdvertisementServiceImpl sut;

    @BeforeEach
    protected void beforeSetup() {
        Mockito.lenient()
                .when(mapperContext.getMapper(Mockito.any()))
                .thenReturn(advertisementMapper);
    }

    @Test
    void saving_advertisement_should_saved_by_calling_repository() {
        // default configuration begin
        Advertisement notSavedAdvertisement = Mockito.mock(Advertisement.class);
        Mockito.lenient()
                .when(advertisementMapper.toAdvertisement(Mockito.any(AdvertisementRequestDto.class)))
                .thenReturn(notSavedAdvertisement);

        Advertisement savedAdvertisement = Mockito.mock(Advertisement.class);
        Mockito.when(savedAdvertisement.getId())
                .thenReturn(TestObjects.SAVED_ENTITY_ID);
        // default configuration end
        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
                .thenReturn(savedAdvertisement);

        AdvertisementRequestDto notSavedAdvertisementDto = Mockito.mock(AdvertisementRequestDto.class);

        //act
        long createdAdvertisementId = sut.createAdvertisement(notSavedAdvertisementDto);

        //assert
        Assertions.assertEquals(TestObjects.SAVED_ENTITY_ID, createdAdvertisementId);
    }

    @Test
    void can_remove_advertisement() {
        final long ID = TestObjects.SAVED_ENTITY_ID;

        sut.removeAdvertisementById(ID);

        Mockito.verify(advertisementRepository).deleteById(ID);
    }

    @Test
    void should_return_page_with_advertisements_when_provided_user_id() {
        long userId = TestObjects.SAVED_ENTITY_ID;
        Pageable pageable = Mockito.mock(Pageable.class);
        Page<Advertisement> returnPage = Mockito.mock(Page.class);
        Mockito.when(advertisementRepository.findAllByOwnerId(userId, pageable))
                .thenReturn(returnPage);

        PageDto pageDto = Mockito.mock(PageDto.class);
        Mockito.when(mapperContext.getMapper(Mockito.any(AdvertisementType.class))
                        .toPageDto(returnPage))
                .thenReturn(pageDto);

        //act
        PageDto<AdvertisementResponseDto> result = sut.getPageByUserId(userId, pageable);

        Assertions.assertEquals(pageDto, result);
    }

    @Test
    void should_return_advertisement_when_exists_by_id() {
        final long ID = TestObjects.SAVED_ENTITY_ID;

        Advertisement mock = Mockito.mock(Advertisement.class);
        Mockito.when(advertisementRepository.findById(ID)).thenReturn(Optional.of(mock));

        // act
        AdvertisementResponseDto oneByAdvertisementId = sut.getOneByAdvertisementId(ID);

        Mockito.verify(advertisementRepository).findById(ID);
    }

    @Test
    void should_throw_exception_when_not_exist_by_id() {
        final long ID = TestObjects.NOT_SAVED_ENTITY_ID;

        Assertions.assertThrows(NotFoundEntityException.class, () -> sut.getOneByAdvertisementId(ID));
    }
}