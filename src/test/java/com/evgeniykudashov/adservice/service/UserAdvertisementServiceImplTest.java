package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.mapper.AdvertisementMapper;
import com.evgeniykudashov.adservice.model.ViewedAdvertisement;
import com.evgeniykudashov.adservice.model.advertisement.Advertisement;
import com.evgeniykudashov.adservice.model.advertisement.AdvertisementStatus;
import com.evgeniykudashov.adservice.model.user.User;
import com.evgeniykudashov.adservice.repository.AdvertisementRepository;
import com.evgeniykudashov.adservice.repository.UserRepository;
import com.evgeniykudashov.adservice.repository.ViewedAdvertisementRepository;
import com.evgeniykudashov.adservice.service.factory.AdvertisementFactory;
import com.evgeniykudashov.adservice.service.impl.UserAdvertisementServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAdvertisementServiceImplTest {

    @Mock
    private AdvertisementRepository advertisementRepository;

    @Mock
    private ViewedAdvertisementRepository viewedAdvertisementRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AdvertisementMapper advertisementMapper;

    @Mock
    private Converter<Principal, Long> principalConverter;

    @Mock
    private AdvertisementFactory factory;

    @InjectMocks
    private UserAdvertisementServiceImpl advertisementService;

    @Test
    public void createAdvertisementByPrincipal_ShouldSaveAdvertisement() {
        // Arrange
        Principal principal = mock(Principal.class);
        AdvertisementRequestDto dto = Mockito.mock(AdvertisementRequestDto.class);
        Advertisement advertisement = Mockito.mock(Advertisement.class);

//        when(factory.createAdvertisement(any(), any(), any(), any()))
//                .thenReturn(advertisement);
        when(advertisementRepository.save(advertisement))
                .thenReturn(advertisement);

        // Act
        long advertisementId = advertisementService.createAdvertisementByPrincipal(principal, dto);

        // Assert
        assertEquals(advertisement.getId(), advertisementId);
    }

    @Test
    public void hideAdvertisementById_ShouldUpdateAdvertisementStatusToHidden() {
        // Arrange
        Principal principal = mock(Principal.class);
        long advertisementId = 1L;
        Advertisement advertisement = new Advertisement();
        when(principalConverter.convert(principal)).thenReturn(1L);
        when(advertisementRepository.findByAdvertisementIdAndSellerId(advertisementId, 1L)).thenReturn(Optional.of(advertisement));

        // Act
        advertisementService.hideAdvertisementById(principal, advertisementId);

        // Assert
        assertEquals(AdvertisementStatus.HIDDEN, advertisement.getStatus());
    }

    @Test
    public void removeAdvertisementById_ShouldDeleteAdvertisement() {
        // Arrange
        Principal principal = mock(Principal.class);
        long advertisementId = 1L;
        Advertisement advertisement = new Advertisement();
        when(principalConverter.convert(principal)).thenReturn(1L);
        when(advertisementRepository.findByAdvertisementIdAndSellerId(advertisementId, 1L)).thenReturn(Optional.of(advertisement));

        // Act
        advertisementService.removeAdvertisementById(principal, advertisementId);

        // Assert
        verify(advertisementRepository).delete(advertisement);
    }

    @Test
    public void getAdvertisementPage_ShouldReturnPageDto() {
        // Arrange
        Principal principal = mock(Principal.class);
        Pageable pageable = mock(Pageable.class);
        Page<Advertisement> advertisements = mock(Page.class);
        PageDto<AdvertisementResponseDto> expectedPageDto = new PageDto<>();
        when(principalConverter.convert(principal)).thenReturn(1L);
        when(advertisementRepository.findAdvertisementsBySellerId(1L, pageable)).thenReturn(advertisements);
        when(advertisementMapper.toPageDto(advertisements)).thenReturn(expectedPageDto);

        // Act
        PageDto<AdvertisementResponseDto> result = advertisementService.getAdvertisementPage(principal, pageable);

        // Assert
        assertEquals(expectedPageDto, result);
    }

    @Test
    public void activateAdvertisementById_ShouldUpdateAdvertisementStatusToActive() {
        // Arrange
        Principal principal = mock(Principal.class);
        long advertisementId = 1L;
        Advertisement advertisement = new Advertisement();
        when(principalConverter.convert(principal)).thenReturn(1L);
        when(advertisementRepository.findByAdvertisementIdAndSellerId(advertisementId, 1L)).thenReturn(Optional.of(advertisement));

        // Act
        advertisementService.activateAdvertisementById(principal, advertisementId);

        // Assert
        assertEquals(AdvertisementStatus.ACTIVE, advertisement.getStatus());
    }

    @Test
    public void addViewedAdvertisementById_ShouldSaveViewedAdvertisement() {
        // Arrange
        Principal principal = mock(Principal.class);
        Advertisement advertisement = Mockito.mock(Advertisement.class);
        User user = Mockito.mock(User.class);
        final long USER_ID = 1L;
        final long ADVERTISEMENT_ID = 1L;

        when(principalConverter.convert(principal))
                .thenReturn(USER_ID);

        when(advertisementRepository.getReferenceById(ADVERTISEMENT_ID))
                .thenReturn(advertisement);
        when(userRepository.getReferenceById(USER_ID))
                .thenReturn(user);

        // Act
        advertisementService.addViewedAdvertisementById(principal, ADVERTISEMENT_ID);


        // Assert
        verify(viewedAdvertisementRepository).save(any(ViewedAdvertisement.class));
    }

    @Test
    public void getViewedAdvertisements_ShouldReturnListAdvertisementResponseDto() {
        // Arrange
        Principal principal = mock(Principal.class);
        Pageable pageable = mock(Pageable.class);
        Long userId = 1L;
        List<Advertisement> viewedAdvertisements = new ArrayList<>();
        List<AdvertisementResponseDto> expectedResponseDtoList = new ArrayList<>();
        when(principalConverter.convert(principal)).thenReturn(userId);
        when(advertisementRepository.findViewedAdvertisementsByUserId(userId, pageable)).thenReturn(viewedAdvertisements);
        when(advertisementMapper.toResponseDto(viewedAdvertisements)).thenReturn(expectedResponseDtoList);

        // Act
        List<AdvertisementResponseDto> result = advertisementService.getViewedAdvertisements(principal, pageable);

        // Assert
        assertEquals(expectedResponseDtoList, result);
    }

}