package com.evgeniykudashov.adservice.model.domain.aggregate.category;

import com.evgeniykudashov.adservice.model.shared.Title;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(onConstructor = @__({@Deprecated}))

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


    public Category(long id, Title title, Category parent, List<Category> childrens) {
        this.id = id;
        this.title = title;
        this.parent = parent;
        this.childrens = childrens;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void addChildren(Category category) {
        this.childrens.add(category);
    }
}
