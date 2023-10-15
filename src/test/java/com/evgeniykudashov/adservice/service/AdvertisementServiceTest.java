package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.service.impl.SearchAdvertisementServiceImpl;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Disabled

@ExtendWith(MockitoExtension.class)
@Setter(onMethod_ = @Autowired)
class AdvertisementServiceTest {

    @Mock
    AdvertisementRepository advertisementRepository;

    @Mock(stubOnly = true)
//    AdvertisementMapperProxy advertisementMapper;

    @InjectMocks
    SearchAdvertisementServiceImpl sut;

    @Disabled
    @Test
    void createAdvertisement_should_save_and_return_id() {
        // Arrange
//        AdvertisementRequestDto requestDto = Mockito.mock(AdvertisementRequestDto.class);
//        Advertisement savedAdvertisement = Mockito.mock(Advertisement.class);
//
//
//        Mockito.when(savedAdvertisement.getId()).thenReturn(TestObjects.SAVED_ENTITY_ID);
//        Mockito.when(advertisementMapper.toAdvertisement(Mockito.any(AdvertisementRequestDto.class)))
//                .thenReturn(savedAdvertisement);
//        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
//                .thenReturn(savedAdvertisement);
//
//        // Act
//        long createdId = sut.createAdvertisement(requestDto);
//
//        // Assert
//        Assertions.assertEquals(TestObjects.SAVED_ENTITY_ID, createdId);
    }

    @Test
    void removeAdvertisementById_should_call_repository_delete() {
        // Arrange
        long advertisementId = 1L;

        // Act
        sut.removeAdvertisementById(advertisementId);

        // Assert
        Mockito.verify(advertisementRepository).deleteById(advertisementId);
    }

    @Test
    void getPageByUserId_should_return_pageDto() {
        // Arrange
//        long userId = TestObjects.SAVED_ENTITY_ID;
//        Pageable pageable = Mockito.mock(Pageable.class);
//        Page<Advertisement> mockPage = Mockito.mock(Page.class);
//        PageDto<AdvertisementResponseDto> value = Mockito.mock(PageDto.class);
//
//        Mockito.when(advertisementRepository.findAllByOwnerId(userId, pageable))
//                .thenReturn(mockPage);
//        Mockito.when(advertisementMapper.toPageDto(mockPage))
//                .thenReturn((PageDto) value);
//
//        // Act
//        PageDto<AdvertisementResponseDto> result = sut.getPageByUserId(userId, pageable);
//
//        // Assert
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(mockPage.getTotalElements(), result.getTotalElements());
    }

    @Test
    void getOneByAdvertisementId_should_return_advertisementResponseDto_when_found() {
        // Arrange
//        Mockito.when(advertisement.getId())
//                .thenReturn(advertisementId);
//        Mockito.when(responseDto.getAdvertisementId())
//                .thenReturn(advertisementId);
//        Mockito.when(advertisementMapper.toResponseDto(advertisement))
//                .thenReturn(responseDto);
//        Mockito.when(advertisementRepository.findById(advertisementId))
//                .thenReturn(Optional.of(advertisement));
//
//         Act
//        AdvertisementResponseDto result = sut.getOneByAdvertisementId(advertisementId);
//
//         Assert
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(advertisement.getId(), result.getAdvertisementId());
    }

    @Test
    void getOneByAdvertisementId_should_throw_NotFoundEntityException_when_not_found() {
        // Arrange
        long advertisementId = 1L;

        Mockito.when(advertisementRepository.findById(advertisementId))
                .thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(NotFoundEntityException.class, () -> sut.getOneByAdvertisementId(advertisementId));
    }
}