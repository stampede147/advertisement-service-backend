package com.evgeniykudashov.adservice.model.domain.aggregate.category;

import com.evgeniykudashov.adservice.TestValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class CategoryTest {

    @Mock
    List<Category> childrens;

    @InjectMocks
    Category sut;

    @Test
    void should_add_children() {
        Category child = TestValues.getCategoryObject();

        sut.addChildren(child);

        Mockito.verify(childrens).add(child);
    }


}