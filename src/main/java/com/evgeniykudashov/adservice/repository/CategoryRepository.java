package com.evgeniykudashov.adservice.repository;

import com.evgeniykudashov.adservice.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select c from Category c left join fetch c.children where c.parent = null")
    Optional<Category> findRootCategory();

    @Query("SELECT c FROM Category c where c.title like %:title%")
    List<Category> findAllByTitle(String title);
}
