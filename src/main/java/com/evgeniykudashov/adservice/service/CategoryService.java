package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.shared.Title;

import java.util.List;

public interface CategoryService {

    long create(Category category);

    void updateTitle(Title title, long categoryId);

    void addChildren(long childrenId, long parentId);

    void removeChildren(long childrenId, long parentId);

    void remove(long categoryId);

    Category findById(long id);

    List<Category> findAllRoots();
}
