package com.evgeniykudashov.adservice.controller.rest;


import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Long> create(Category category) {
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(categoryService.create(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        categoryService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

}
