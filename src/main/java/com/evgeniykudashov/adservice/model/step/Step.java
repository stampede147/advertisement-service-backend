package com.evgeniykudashov.adservice.model.step;


import com.evgeniykudashov.adservice.model.category.Category;
import com.evgeniykudashov.adservice.model.field.Field;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "adv_layout_steps")
public class Step {

    @Id
    @Column(name = "step_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    @Column(name = "order_c")
    private long order;

    @OneToMany(mappedBy = "step")
    private List<Field> children;

}
