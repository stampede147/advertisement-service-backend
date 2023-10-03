package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.dto.response.CategoryResponseDto;
import com.evgeniykudashov.adservice.mapper.CategoryMapper;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto getRootCategory() {
        return categoryRepository.findRootCategory()
                .map(categoryMapper::toResponseDto)
                .orElseThrow();
    }
}
