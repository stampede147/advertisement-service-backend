package com.evgeniykudashov.adservice.model.domain.category;

import com.evgeniykudashov.adservice.model.shared.Title;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Title title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> childrens;


    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addChildren(Category category) {
        this.childrens.add(category);
    }
}
