package com.evgeniykudashov.adservice.model.domain.aggregate.category;

import com.evgeniykudashov.adservice.model.domain.shared.Title;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor(onConstructor = @__({@Deprecated}))
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    private Title title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Category> childrens;


    public Category(Title title, Category parent, List<Category> childrens) {
        this.title = title;
        this.parent = parent;
        this.childrens = childrens;
    }

    private void setParent(Category parent) {
        this.parent = parent;
    }

    public void addChildren(Category category) {
        category.setParent(this);
        this.childrens.add(category);
    }
}
