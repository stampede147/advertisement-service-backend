package com.evgeniykudashov.adservice.model.field;


import com.evgeniykudashov.adservice.model.step.Step;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "adv_layout_fields")
public class Field {

    @Id
    @Column(name = "field_id")
    private long id;

    private String name;

    private String datatype;

    private String label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "step_id")
    private Step step;

    @Column(name = "order_c")
    private long order;

    @Enumerated(value = EnumType.STRING)
    private FieldType type;

    private String value;

}
