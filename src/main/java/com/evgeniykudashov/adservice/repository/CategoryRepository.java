package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
