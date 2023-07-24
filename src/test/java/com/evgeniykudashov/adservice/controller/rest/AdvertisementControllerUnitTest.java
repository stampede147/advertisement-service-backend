package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.dto.response.AdvertisementResponseDto;
import com.evgeniykudashov.adservice.dto.response.PageDto;
import com.evgeniykudashov.adservice.service.AdvertisementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class AdvertisementControllerUnitTest {

    @Mock
    AdvertisementService advertisementService;

    @InjectMocks
    AdvertisementController sut;

    @BeforeEach
    void init() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    void onCreate() {
        AdvertisementRequestDto dto = Mockito.mock(AdvertisementRequestDto.class);

        ResponseEntity<Void> voidResponseEntity = sut.onCreate(dto);

        Mockito.verify(advertisementService).createAdvertisement(dto);
    }

    @Test
    void onDelete() {
        long ID = 1L;

        sut.onDelete(ID);

        Mockito.verify(advertisementService).removeAdvertisementById(ID);
    }

    @Test
    void onGetById() {
        long ID = 1L;

        ResponseEntity<? extends AdvertisementResponseDto> result = sut.onGetById(ID);

        Assertions.assertNotNull(result);
        Mockito.verify(advertisementService).getOneByAdvertisementId(ID);
    }

    @Test
    void getUserAdvertisements() {
        Pageable mock = Mockito.mock(Pageable.class);
        long userId = 1L;

        ResponseEntity<PageDto<? extends AdvertisementResponseDto>> result = sut.getUserAdvertisements(userId, mock);

        Assertions.assertNotNull(result);
        Mockito.verify(advertisementService).getPageByUserId(userId, mock);
    }
}