package com.evgeniykudashov.adservice.model.category;

import com.evgeniykudashov.adservice.model.advertisement.valueobject.Title;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;
    @Getter
    private Title title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> childrens;

}
