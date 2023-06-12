package com.evgeniykudashov.adservice.controller.repository;

import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.parent = null")
    public List<Category> findRootCategories();

}
