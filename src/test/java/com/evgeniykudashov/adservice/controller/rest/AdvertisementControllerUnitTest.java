package com.evgeniykudashov.adservice.controller.rest;

import com.evgeniykudashov.adservice.dto.request.AdvertisementRequestDto;
import com.evgeniykudashov.adservice.service.SearchAdvertisementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Disabled
@ExtendWith(MockitoExtension.class)
class AdvertisementControllerUnitTest {

    @Mock
    SearchAdvertisementService advertisementService;


    @BeforeEach
    void init() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @Test
    void onCreate() {
        AdvertisementRequestDto dto = Mockito.mock(AdvertisementRequestDto.class);

    }

    @Test
    void onDelete() {
        long ID = 1L;


//        Mockito.verify(advertisementService).removeAdvertisementById(ID);
    }

    @Test
    void onGetById() {
        long ID = 1L;

        Mockito.verify(advertisementService).getOneByAdvertisementId(ID);
    }

    @Test
    void getUserAdvertisements() {
        Pageable mock = Mockito.mock(Pageable.class);
        long userId = 1L;


        Mockito.verify(advertisementService).getPageByUserId(userId, mock);
    }
}