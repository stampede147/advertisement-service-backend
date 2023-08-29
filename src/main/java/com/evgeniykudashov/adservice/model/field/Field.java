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

    private String datatype;

    private String label;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "step_id")
    private Step step;

    private long order;

    @Enumerated(value = EnumType.STRING)
    private FieldType type;

    private String value;

}
