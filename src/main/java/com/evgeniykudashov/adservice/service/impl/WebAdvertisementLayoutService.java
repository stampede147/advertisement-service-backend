package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.request.LayoutRequestDto;
import com.evgeniykudashov.adservice.dto.response.LayoutResponseDto;
import com.evgeniykudashov.adservice.mapper.FieldMapper;
import com.evgeniykudashov.adservice.mapper.StepMapper;
import com.evgeniykudashov.adservice.model.field.Field;
import com.evgeniykudashov.adservice.model.step.Step;
import com.evgeniykudashov.adservice.repository.FieldRepository;
import com.evgeniykudashov.adservice.repository.StepRepository;
import com.evgeniykudashov.adservice.service.EditAdvertisementLayoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebAdvertisementLayoutService implements EditAdvertisementLayoutService {

    private final FieldRepository fieldRepository;
    private final StepRepository stepRepository;

    private final FieldMapper fieldMapper;
    private final StepMapper stepMapper;


    @Override
    public LayoutResponseDto getLayout(LayoutRequestDto dto) {

        List<Step> steps = stepRepository.getStepsByCategoryId(dto.getCategoryId());

        List<Field> fields = fieldRepository.getFieldsByStepIds(steps.stream()
                .mapToLong(Step::getId)
                .toArray());

        return new LayoutResponseDto(fieldMapper.toResponseDto(fields), stepMapper.toResponseDto(steps));
    }

}