package com.evgeniykudashov.adservice.service.impl;

import com.evgeniykudashov.adservice.exception.NotFoundCategoryException;
import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    @Override
    @Transactional()
    public long create(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    @Transactional()
    public void updateTitle(Title title, long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);
        category.updateTitle(title);
        categoryRepository.save(category);
    }

    @Override
    @Transactional()
    public void addChildren(long childrenId, long parentId) {
        Category parent = categoryRepository.findById(parentId).orElseThrow(NotFoundCategoryException::new);
        Category children = categoryRepository.findById(childrenId).orElseThrow(NotFoundCategoryException::new);
        parent.addChildren(children);
        categoryRepository.save(parent);
    }

    @Override
    @Transactional()
    public void removeChildren(long childrenId, long parentId) {
        Category parent = categoryRepository.findById(parentId).orElseThrow(NotFoundCategoryException::new);
        Category children = categoryRepository.findById(childrenId).orElseThrow(NotFoundCategoryException::new);
        parent.addChildren(children);
        categoryRepository.save(parent);
    }

    @Override
    @Transactional()
    public void remove(long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(NotFoundCategoryException::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAllRoots() {
        return categoryRepository.findRootCategories();
    }
}
