package com.evgeniykudashov.adservice.model.advertisement;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "shoe_sizes")
public class ShoeSize {

    @Id
    private int size;

    private String description;

}
