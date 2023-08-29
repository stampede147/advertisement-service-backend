package com.evgeniykudashov.adservice.model.step;


import com.evgeniykudashov.adservice.model.field.Field;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "adv_layout_steps")
public class Step {

    @Id
    @Column(name = "step_id")
    private Long id;

    private String title;

    private long order;

    @OneToMany(mappedBy = "step")
    private List<Field> children;

}
