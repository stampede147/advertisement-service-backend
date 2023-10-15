package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.response.EditLayoutResponseDto;
import com.evgeniykudashov.adservice.dto.response.NavigationResponseDto;
import com.evgeniykudashov.adservice.exception.servicelayer.NotFoundEntityException;
import com.evgeniykudashov.adservice.mapper.AdvertisementEditMapper;
import com.evgeniykudashov.adservice.model.advertisementEdit.EditLayoutStep;
import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.repository.EditLayoutStepRepository;
import com.evgeniykudashov.adservice.service.AdvertisementEditService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
public class AdvertisementEditServiceImpl implements AdvertisementEditService {

    private final CategoryRepository categoryRepository;

    private final EditLayoutStepRepository stepRepository;

    private final AdvertisementEditMapper navigationMapper;


    @Override
    @Transactional(readOnly = true)
    public NavigationResponseDto loadNavigation() {
        Category category = categoryRepository.findRootCategory()
                .orElseThrow(NotFoundEntityException::new);

        return navigationMapper.toResponseDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public EditLayoutResponseDto loadLayout(@Validated NavigationResponseDto.NavigationDto dto) {

        List<EditLayoutStep> steps = stepRepository.findAllByCategoryIdOrderByPosition(dto.getCategoryId());

        return navigationMapper.toResponseDto(null, steps);
    }
}
