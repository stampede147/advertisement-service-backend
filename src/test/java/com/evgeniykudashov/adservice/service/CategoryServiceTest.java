package com.evgeniykudashov.adservice.service;

import com.evgeniykudashov.adservice.model.domain.aggregate.category.Category;
import com.evgeniykudashov.adservice.model.domain.shared.Title;
import com.evgeniykudashov.adservice.repository.CategoryRepository;
import com.evgeniykudashov.adservice.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    public static final Long ID = 1L;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryServiceImpl sut;

    @Mock
    Category category;

    @Test
    void create() {
        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        long expectedId = sut.create(category);

        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    void updateTitle() {
        Title title = new Title();
        Mockito.when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        sut.updateTitle(title, ID);

        Mockito.verify(category).updateTitle(title);
        Mockito.verify(categoryRepository).findById(ID);
        Mockito.verify(categoryRepository).save(category);
    }

    @Test
    void addChildren() {
        Category children = Mockito.mock(Category.class);
        Mockito.when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findById(ID + 1)).thenReturn(Optional.of(children));

        sut.addChildren(ID + 1, ID);

        Mockito.verify(category).addChildren(children);
        Mockito.verify(categoryRepository).findById(ID);
        Mockito.verify(categoryRepository).findById(ID + 1);
    }

    @Test
    void removeChildren() {
        Category children = Mockito.mock(Category.class);
        Mockito.when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));
        Mockito.when(categoryRepository.findById(ID + 1)).thenReturn(Optional.of(children));

        sut.removeChildren(ID + 1, ID);

        Mockito.verify(category).removeChildren(children);
        Mockito.verify(categoryRepository).findById(ID);
        Mockito.verify(categoryRepository).findById(ID + 1);
    }

    @Test
    void remove() {

        sut.remove(ID);

        Mockito.verify(categoryRepository).deleteById(ID);
    }

    @Test
    void findById() {
        Mockito.when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        Category expected = sut.findById(ID);

        Assertions.assertEquals(expected, category);
        Mockito.verify(categoryRepository).findById(ID);
    }

    @Test
    void findAllRoots() {
        Category root = Mockito.mock(Category.class);
        Mockito.when(categoryRepository.findRootCategories()).thenReturn(List.of(root));

        List<Category> expected = sut.findAllRoots();

        Assertions.assertEquals(expected, List.of(root));
        Mockito.verify(categoryRepository).findRootCategories();
    }
}