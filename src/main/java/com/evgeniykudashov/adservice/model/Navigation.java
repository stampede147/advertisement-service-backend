package com.evgeniykudashov.adservice.model;

import com.evgeniykudashov.adservice.model.category.Category;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "navigations")
public class Navigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "navigation_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "navigation_parent_id")
    private Navigation parent;

    @OneToMany(mappedBy = "parent")
    private List<Navigation> children;
}
