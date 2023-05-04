package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.view.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    void remove(CategoryDto categoryDto);

    Category findById(long id);

    List<CategoryDto> findAllRoots();
}
